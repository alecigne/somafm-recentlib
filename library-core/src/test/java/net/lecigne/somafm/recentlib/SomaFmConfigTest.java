package net.lecigne.somafm.recentlib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import java.net.URI;
import java.util.stream.Stream;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("SomaFmConfig baseUrl validation")
class SomaFmConfigTest {

  @ParameterizedTest
  @ValueSource(strings = {"https://somafm.com", "http://somafm.com"})
  void should_accept_valid_parameters(
      // Given
      String url
  ) {
    var expectedConfig = new SomaFmConfig(URI.create(url), "ua");

    // When
    var config = SomaFmConfig.of(url, "ua");

    // Then
    assertThat(config).isEqualTo(expectedConfig);
  }

  @ParameterizedTest
  @NullAndEmptySource
  void should_reject_null_or_blank_url(
      // Given
      String emptyUrl
  ) {
    // "When"
    ThrowingCallable call = () -> SomaFmConfig.of(emptyUrl, "ua");

    // Then
    assertThatThrownBy(call)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("A URL must be provided.");
  }

  @ParameterizedTest
  @MethodSource
  void should_reject_invalid_url(
      // Given
      String url, String errMsg
  ) {
    // "When"
    ThrowingCallable call = () -> SomaFmConfig.of(url, "ua");

    // Then
    assertThatThrownBy(call)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(errMsg);
  }

  private static Stream<Arguments> should_reject_invalid_url() {
    return Stream.of(
        argumentSet("Bad syntax", "https:// somafm.com/", Fixtures.BAD_SYNTAX),
        argumentSet("Not absolute", "/recent", Fixtures.NOT_ABSOLUTE),
        argumentSet("Wrong scheme", "ftp://somafm.com/", Fixtures.WRONG_SCHEME)
    );
  }

  @ParameterizedTest
  @NullAndEmptySource
  void should_check_user_agent(
      // Given
      String userAgent
  ) {
    // "When"
    ThrowingCallable call = () -> SomaFmConfig.of("https://somafm.com/", userAgent);

    // Then
    assertThatThrownBy(call)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("User agent must be provided.");
  }

  static class Fixtures {
    static final String BAD_SYNTAX = "Invalid URL: 'https:// somafm.com/'";
    static final String NOT_ABSOLUTE = "Invalid URL: '/recent' is not absolute";
    static final String WRONG_SCHEME = "Invalid URL: scheme 'ftp' is unsupported";
  }

}
