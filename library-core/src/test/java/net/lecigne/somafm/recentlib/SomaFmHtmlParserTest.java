package net.lecigne.somafm.recentlib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("The SomaFM HTML broadcast parser")
@SuppressWarnings("HttpUrlsUsage")
class SomaFmHtmlParserTest {

  private static final SomaFmHtmlParser SOMA_FM_HTML_PARSER = new SomaFmHtmlParser();

  @Test
  void should_throw_when_no_table() {
    // Given
    String noTableHtml = Fixtures.noTable();

    // When
    ThrowingCallable call = () -> SOMA_FM_HTML_PARSER.parse(noTableHtml);

    // Then
    assertThatThrownBy(call)
        .isInstanceOf(SomaFmException.class)
        .hasMessage("Error while parsing HTML broadcasts.");
  }

  @Test
  void should_yield_correct_number_of_broadcasts() throws IOException {
    // Given
    URL url = Resources.getResource("data/dronezone.html");
    String text = Resources.toString(url, StandardCharsets.UTF_8);

    // When
    List<RawBroadcast> broadcasts = SOMA_FM_HTML_PARSER.parse(text);

    // Then
    assertThat(broadcasts).hasSize(20);
  }

  @Test
  void should_parse_nominal_song() {
    // Given
    String nominal = Fixtures.nominal();
    var expected = RawBroadcast.builder()
        .time("03:36:43")
        .artist("Dirk Serries' Microphonics")
        .artistLink("http://dirkserriesmicrophonics.bandcamp.com")
        .title("VI")
        .album("microphonics VI - XX")
        .build();

    // When
    List<RawBroadcast> rawBroadcasts = SOMA_FM_HTML_PARSER.parse(nominal);

    // Then
    assertThat(rawBroadcasts)
        .hasSize(1)
        .first()
        .isEqualTo(expected);
  }

  @Test
  void should_parse_breaks() {
    // Given
    String breakStationId = Fixtures.breakStationId();
    var expected = RawBroadcast.builder()
        .time("03:19:55")
        .title("Break / Station ID")
        .build();

    // When
    List<RawBroadcast> rawBroadcasts = SOMA_FM_HTML_PARSER.parse(breakStationId);

    // Then
    assertThat(rawBroadcasts)
        .hasSize(1)
        .first()
        .isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource
  void should_parse_song_without_artist_link(String rawSong) {
    // Given
    var expected = RawBroadcast.builder()
        .time("03:36:43")
        .artist("Dirk Serries' Microphonics")
        .title("VI")
        .album("microphonics VI - XX")
        .build();

    // When
    List<RawBroadcast> rawBroadcasts = SOMA_FM_HTML_PARSER.parse(rawSong);

    // Then
    assertThat(rawBroadcasts)
        .hasSize(1)
        .first()
        .isEqualTo(expected);
  }

  public static Stream<Arguments> should_parse_song_without_artist_link() {
    return Stream.of(
        arguments(Fixtures.noLink()),
        arguments(Fixtures.noHrefInLink())
    );
  }

  @Test
  void should_parse_song_with_cgi_link() {
    // Given
    String cgiLink = Fixtures.cgiLink();
    var expected = RawBroadcast.builder()
        .time("01:35:56")
        .artist("Eivind Aarset and Jan Bang")
        .artistLink("https://somafm.com/buy/multibuy.cgi?mode=amazon&title=Nightspell&artist=Eivind%20Aarset%20and%20Jan%20Bang")
        .title("Nightspell")
        .album("Snow Catches on Her Eyelashes")
        .build();

    // When
    List<RawBroadcast> rawBroadcasts = SOMA_FM_HTML_PARSER.parse(cgiLink);

    // Then
    assertThat(rawBroadcasts)
        .hasSize(1)
        .first()
        .isEqualTo(expected);
  }

  static class Fixtures {

    static String noTable() {
      return "<p>Hello!</p>";
    }

    static String breakStationId() {
      return """
          <table>
            <tr></tr>
            <tr></tr>
            <tr>
              <td>03:19:55</td>
              <td colspan="4"><span class="dim">Break / Station ID</span></td>
            </tr>
            <tr></tr>
          </table>
          """;
    }

    static String nominal() {
      return """
          <table>
            <tr></tr>
            <tr></tr>
            <tr>
              <td>03:36:43</td>
              <td><a href="http://dirkserriesmicrophonics.bandcamp.com">Dirk Serries' Microphonics</a></td>
              <td>VI</td>
              <td>microphonics VI - XX</td>
            </tr>
            <tr></tr>
          </table>
          """;
    }

    static String noLink() {
      return """
          <table>
            <tr></tr>
            <tr></tr>
            <tr>
              <td>03:36:43</td>
              <td>Dirk Serries' Microphonics</td>
              <td>VI</td>
              <td>microphonics VI - XX</td>
            </tr>
            <tr></tr>
          </table>
          """;
    }

    static String noHrefInLink() {
      return """
          <table>
            <tr></tr>
            <tr></tr>
            <tr>
              <td>03:36:43</td>
              <td><a>Dirk Serries' Microphonics</a></td>
              <td>VI</td>
              <td>microphonics VI - XX</td>
            </tr>
            <tr></tr>
          </table>
          """;
    }

    static String cgiLink() {
      return """
          <table>
            <tr></tr>
            <tr></tr>
            <tr>
              <td>01:35:56</td>
              <td>
                <a href="/buy/multibuy.cgi?mode=amazon&amp;title=Nightspell&amp;artist=Eivind%20Aarset%20and%20Jan%20Bang">Eivind Aarset and Jan Bang</a>
              </td>
              <td>Nightspell</td>
              <td>Snow Catches on Her Eyelashes</td>
              <td></td>
            </tr>
            <tr></tr>
          </table>
          """;
    }

  }

}
