package net.lecigne.somafm.recentlib;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

/**
 * Fetch and parse the recently played tracks page of a given channel.
 */
@Slf4j
class SomaFmHtmlClient {

  private final SomaFmConfig config;
  private final HttpClient httpClient;

  SomaFmHtmlClient(SomaFmConfig config, HttpClient httpClient) {
    this.config = config;
    this.httpClient = httpClient;
  }

  String fetchHtml(Channel predefinedChannel) {
    var request = HttpRequest.newBuilder()
        .uri(createChannelUri(predefinedChannel))
        .timeout(Duration.ofSeconds(10))
        .header("User-Agent", config.userAgent())
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return response.body();
      } else {
        throw new SomaFmException("Unexpected response status: " + response.statusCode());
      }
    } catch (IOException | InterruptedException e) {
      throw handleFetchException(e);
    }
  }

  private SomaFmException handleFetchException(Exception e) {
    var error = "Error while fetching recent broadcasts";
    log.error(error, e);
    if (e instanceof InterruptedException) Thread.currentThread().interrupt();
    throw new SomaFmException(error, e);
  }

  private URI createChannelUri(Channel predefinedChannel) {
    return config.baseUri().resolve(predefinedChannel.toRecentPath());
  }

  static SomaFmHtmlClient of(SomaFmConfig config) {
    return new SomaFmHtmlClient(
        config,
        HttpClient.newBuilder()
            .version(Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(10))
            .build()
    );
  }

}
