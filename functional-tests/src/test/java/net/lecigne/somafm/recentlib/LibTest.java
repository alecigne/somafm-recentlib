package net.lecigne.somafm.recentlib;

import static net.lecigne.somafm.recentlib.PredefinedChannel.DRONE_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The library")
@Slf4j
class LibTest {

  @Test
  void should_work_in_real_life_conditions() {
    // Given
    var userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
    var somaFm = SomaFm.of("https://somafm.com/", userAgent);

    // When
    List<Broadcast> broadcasts = somaFm.fetchRecent(DRONE_ZONE);

    // Then
    assertThat(broadcasts).hasSize(20);
  }

}
