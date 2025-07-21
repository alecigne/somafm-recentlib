package net.lecigne.somafm.recentlib;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static net.lecigne.somafm.recentlib.PredefinedChannel.DRONE_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("The lib")
@ExtendWith(WireMockExtension.class)
class SomaFmIT {

  @RegisterExtension
  static WireMockExtension wiremock = WireMockExtension.newInstance()
      .options(WireMockConfiguration.wireMockConfig().dynamicPort())
      .build();

  @Test
  void should_work() throws IOException {
    // Given
    URL url = Resources.getResource("data/dronezone.html");
    String html = Resources.toString(url, StandardCharsets.UTF_8);

    wiremock.stubFor(get(urlPathEqualTo("/recent/dronezone.html"))
        .willReturn(aResponse()
            .withStatus(200)
            .withBody(html)));

    var instantFactory = InstantFactory.fixed(Instant.parse("2018-04-29T11:00:00.00Z"));
    var clientConfig = SomaFmConfig.of(wiremock.getRuntimeInfo().getHttpBaseUrl(), "ua");

    var httpClient = HttpClient.newBuilder()
        .version(Version.HTTP_2)
        .followRedirects(Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(10))
        .build();

    var somaFm = new SomaFm(new SomaFmHtmlClient(clientConfig, httpClient), new SomaFmHtmlParser(), new BroadcastMapper(instantFactory));

    List<Broadcast> expected = Fixtures.getExpectedBroadcasts();

    // When
    List<Broadcast> broadcasts = somaFm.fetchRecent(DRONE_ZONE);

    // Then
    assertThat(broadcasts).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
  }

}
