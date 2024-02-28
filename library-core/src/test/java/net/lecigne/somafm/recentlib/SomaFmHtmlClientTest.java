package net.lecigne.somafm.recentlib;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("The SomaFM HTML client")
@SuppressWarnings("unchecked") // ok for tests
class SomaFmHtmlClientTest {

  private final HttpClient httpClient = mock(HttpClient.class);
  private final HttpResponse<String> mockResponse = mock(HttpResponse.class);
  private final SomaFmHtmlClient somaFmHtmlClient = new SomaFmHtmlClient(new SomaFmConfig("http://localhost", ""), httpClient);

  @ParameterizedTest
  @MethodSource
  void should_handle_underlying_client_exceptions(Exception e) throws IOException, InterruptedException {
    // Given
    given(httpClient.send(any(), any())).willThrow(e);

    // When
    ThrowingCallable call = () -> somaFmHtmlClient.fetchHtml(PredefinedChannel.GROOVE_SALAD);

    // Then
    assertThatThrownBy(call)
        .isInstanceOf(SomaFmException.class)
        .hasMessage("Error while fetching recent broadcasts");
  }

  public static Stream<Arguments> should_handle_underlying_client_exceptions() {
    return Stream.of(
        arguments(new IOException()),
        arguments(new InterruptedException())
    );
  }

  @Test
  void should_handle_unexpected_response_status() throws IOException, InterruptedException {
    // Given
    given(mockResponse.statusCode()).willReturn(500);
    given(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).willReturn(mockResponse);

    // When
    ThrowingCallable call = () -> somaFmHtmlClient.fetchHtml(PredefinedChannel.GROOVE_SALAD);

    // Then
    assertThatThrownBy(call)
        .isInstanceOf(SomaFmException.class)
        .hasMessage("Unexpected response status: 500");
  }
}
