package net.lecigne.somafm.recentlib;

import java.time.ZoneId;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unused") // This is the lib's API
public class SomaFm {

  static final String DEFAULT_URL = "https://www.somafm.com";
  static final ZoneId BROADCAST_LOCATION = ZoneId.of("America/Los_Angeles");

  private final SomaFmHtmlClient client;
  private final SomaFmHtmlParser parser;
  private final BroadcastMapper mapper;

  SomaFm(SomaFmHtmlClient client, SomaFmHtmlParser parser, BroadcastMapper mapper) {
    this.client = client;
    this.parser = parser;
    this.mapper = mapper;
  }

  public List<Broadcast> fetchRecent(Channel channel) {
    String htmlPage = client.fetchHtml(channel);
    List<RawBroadcast> rawBroadcasts = parser.parse(htmlPage);
    List<Broadcast> broadcasts = mapper.map(channel, rawBroadcasts);
    log.info("Fetched {} broadcasts from {}", broadcasts.size(), channel);
    return broadcasts;
  }

  public static SomaFm of(String userAgent) {
    return SomaFm.of(DEFAULT_URL, userAgent);
  }

  public static SomaFm of(String baseUrl, String userAgent) {
    if (userAgent == null || userAgent.trim().isEmpty()) {
      throw new IllegalArgumentException("User agent must be provided.");
    }
    var libConfig = SomaFmConfig.of(baseUrl, userAgent);
    return new SomaFm(
        SomaFmHtmlClient.of(libConfig),
        new SomaFmHtmlParser(),
        new BroadcastMapper(InstantFactory.system())
    );
  }

}
