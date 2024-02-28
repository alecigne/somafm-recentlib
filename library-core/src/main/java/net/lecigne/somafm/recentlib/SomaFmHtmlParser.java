package net.lecigne.somafm.recentlib;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class SomaFmHtmlParser {

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
    var localTime = columns.get(0).text().substring(0, 8); // Just keep HH:MM:SS
    var secondField = columns.get(1).text(); // Second field can either be the artist or an announcement
    if ("Break / Station ID".equals(secondField)) {
      return RawBroadcast.builder()
          .time(localTime)
          .artist("n/a")
          .title(secondField)
          .album("n/a")
          .build();
    } else {
      var title = columns.get(2).text();
      var album = columns.get(3).text();
      return RawBroadcast.builder()
          .time(localTime)
          .artist(secondField)
          .title(title)
          .album(album)
          .build();
    }
  }

}
