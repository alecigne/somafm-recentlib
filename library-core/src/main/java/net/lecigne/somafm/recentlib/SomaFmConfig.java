package net.lecigne.somafm.recentlib;

record SomaFmConfig(String baseUrl, String userAgent) {
  static SomaFmConfig of(String baseUrl, String userAgent) {
    return new SomaFmConfig(baseUrl, userAgent);
  }
}
