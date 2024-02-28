package net.lecigne.somafm.recentlib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The SomaFM HTML broadcast parser")
class SomaFmHtmlParserTest {

  private static final SomaFmHtmlParser SOMA_FM_HTML_PARSER = new SomaFmHtmlParser();

  @Test
  void should_parse_correctly() throws IOException {
    // Given
    URL url = Resources.getResource("data/dronezone.html");
    String text = Resources.toString(url, StandardCharsets.UTF_8);
    RawBroadcast expected = RawBroadcast.builder()
        .time("03:36:43")
        .artist("Dirk Serries' Microphonics")
        .title("VI")
        .album("microphonics VI - XX")
        .build();

    // When
    List<RawBroadcast> broadcasts = SOMA_FM_HTML_PARSER.parse(text);

    // Then
    assertThat(broadcasts).hasSize(20);
    RawBroadcast recentPlayDto = broadcasts.get(0);
    assertThat(recentPlayDto).isEqualTo(expected);
  }

  @Test
  void should_throw_when_no_table() throws IOException {
    // Given
    URL url = Resources.getResource("data/dronezone_no_table.html");
    String text = Resources.toString(url, StandardCharsets.UTF_8);

    // When
    ThrowingCallable throwingCallable = () -> SOMA_FM_HTML_PARSER.parse(text);

    // Then
    assertThatThrownBy(throwingCallable)
        .isInstanceOf(SomaFmException.class)
        .hasMessage("Error while parsing HTML broadcasts.");
  }

}
