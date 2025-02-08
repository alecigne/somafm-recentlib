package net.lecigne.somafm.recentlib;

import static net.lecigne.somafm.recentlib.PredefinedChannel.JOLLY_OL_SOUL;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A channel")
class PredefinedChannelTest {

  @Test
  void should_provide_itself_from_internal_name() {
    // Given
    var internalName = "jollysoul";

    // When
    Optional<PredefinedChannel> channelOpt = PredefinedChannel.getByInternalName(internalName);

    // Then
    assertThat(channelOpt).isPresent().hasValue(JOLLY_OL_SOUL);
  }

  @Test
  void should_provide_itself_from_public_name() {
    // Given
    var publicName = "Jolly Ol' Soul";

    // When
    Optional<PredefinedChannel> channelOpt = PredefinedChannel.getByPublicName(publicName);

    // Then
    assertThat(channelOpt).isPresent().hasValue(JOLLY_OL_SOUL);
  }

  @Test
  void should_convert_itself_to_relative_url_path() {
    // When
    URI recentPath = JOLLY_OL_SOUL.toRecentPath();

    // Then
    assertThat(recentPath.getPath()).isEqualTo("recent/jollysoul.html");
  }
}