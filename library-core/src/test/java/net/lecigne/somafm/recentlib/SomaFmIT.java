package net.lecigne.somafm.recentlib;

import static net.lecigne.somafm.recentlib.PredefinedChannel.DRONE_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

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
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The lib")
class SomaFmIT {

  static MockWebServer mockWebServer;

  @BeforeAll
  static void beforeAll() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
    mockWebServer.setDispatcher(getDispatcher());
  }

  @AfterAll
  static void afterAll() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  void should_work() {
    // Given
    // Latest track was played at 03:36:43 UTC-7 so at 10:36:43 UTC. The snapshot time will be
    // rounded to 11:00:00 UTC.
    var instantFactory = InstantFactory.fixed(Instant.parse("2018-04-29T11:00:00.00Z"));
    var clientConfig = SomaFmConfig.of(mockWebServer.url("/").toString(), "ua");
    HttpClient httpClient = HttpClient.newBuilder()
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

  private static Dispatcher getDispatcher() throws IOException {
    URL url = Resources.getResource("data/dronezone.html");
    String html = Resources.toString(url, StandardCharsets.UTF_8);
    return new Dispatcher() {
      @Override
      public MockResponse dispatch(RecordedRequest recordedRequest) {
        return new MockResponse().setResponseCode(200).setBody(html);
      }
    };
  }

}
