package net.lecigne.somafm.recentlib;

import java.net.URI;
import java.net.URISyntaxException;

record SomaFmConfig(URI baseUri, String userAgent) {

  static SomaFmConfig of(String baseUrl, String userAgent) {
    URI uri = checkBaseUrl(baseUrl);
    checkUserAgent(userAgent);
    return new SomaFmConfig(uri, userAgent);
  }

  private static URI checkBaseUrl(String baseUrl) {
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
      throw new IllegalArgumentException("A URL must be provided.");
    }
    String base = baseUrl.trim();
    URI uri = toUri(base);
    String scheme = uri.getScheme();
    if (!uri.isAbsolute()) {
      throw new IllegalArgumentException("Invalid URL: '%s' is not absolute".formatted(uri));
    }
    if (!scheme.equals("http") && !scheme.equals("https")) {
      throw new IllegalArgumentException("Invalid URL: scheme '%s' is unsupported".formatted(scheme));
    }
    return uri;
  }

  private static void checkUserAgent(String userAgent) {
    if (userAgent == null || userAgent.trim().isEmpty()) {
      throw new IllegalArgumentException("User agent must be provided.");
    }
  }

  private static URI toUri(String urlString) {
    try {
      return new URI(urlString);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid URL: '%s'".formatted(urlString), e);
    }
  }

}
