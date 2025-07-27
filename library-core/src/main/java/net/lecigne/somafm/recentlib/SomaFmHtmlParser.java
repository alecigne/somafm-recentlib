package net.lecigne.somafm.recentlib;

import java.util.List;
import java.util.Optional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class SomaFmHtmlParser {

  private static final String BREAK_STATION_ID = "Break / Station ID";

  List<RawBroadcast> parse(String htmlBroadcasts) {
    Document doc = Jsoup.parse(htmlBroadcasts);
    Element table = doc.select("table").first();
    if (table == null) {
      throw new SomaFmException("Error while parsing HTML broadcasts.");
    }
    Elements elements = table.select("tr");
    return elements
        .subList(2, elements.size() - 1)
        .stream()
        .map(this::parseRow)
        .toList();
  }

  private RawBroadcast parseRow(Element row) {
    Elements columns = row.select("td");
    String localTime = columns.get(0).text().substring(0, 8); // Just keep HH:MM:SS
    String secondField = columns.get(1).text(); // Second field can either be the artist or an announcement
    if (BREAK_STATION_ID.equals(secondField)) {
      return RawBroadcast.builder()
          .time(localTime)
          .title(secondField)
          .build();
    } else {
      String artistLink = parseArtistLink(columns);
      var title = columns.get(2).text();
      var album = columns.get(3).text();
      return RawBroadcast.builder()
          .time(localTime)
          .artist(secondField)
          .artistLink(artistLink)
          .title(title)
          .album(album)
          .build();
    }
  }

  private static String parseArtistLink(Elements columns) {
    return Optional.ofNullable(columns.get(1).selectFirst("a"))
        .filter(linkElement -> linkElement.hasAttr("href"))
        .map(linkElement -> {
          String href = linkElement.attr("href");
          return href.startsWith("/") ? "https://somafm.com" + href : href;
        })
        .orElse(null);
  }

}
