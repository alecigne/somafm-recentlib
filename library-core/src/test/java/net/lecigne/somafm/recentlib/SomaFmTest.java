package net.lecigne.somafm.recentlib;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayName("The SomaFM root class")
class SomaFmTest {

  @ParameterizedTest
  @NullAndEmptySource
  void should_check_its_factory_method_args(String userAgent) {
    ThrowingCallable call = () -> SomaFm.of("baseUrl", userAgent);
    assertThatThrownBy(call)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("User agent must be provided.");
  }

}
