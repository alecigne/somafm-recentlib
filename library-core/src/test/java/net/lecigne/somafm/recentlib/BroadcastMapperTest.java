package net.lecigne.somafm.recentlib;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The broadcast mapper")
class BroadcastMapperTest {

  // The goal here is not to test TimeUtils further -- see dedicated tests for that
  @Test
  void should_map_raw_broadcasts_to_broadcasts() {
    // Given
    var channel = PredefinedChannel.DRONE_ZONE;
    // 16:00 UTC at snapshot time
    var instantFactory = InstantFactory.fixed(Instant.parse("2024-09-08T16:00:00.00Z"));
    var broadcast = RawBroadcast.builder()
        .time("08:00:00") // tracked played at 08:00 UTC-7 -> 15:00 UTC, 1 hour ago
        .artist("artist")
        .artistLink("artistLink")
        .title("title")
        .album("album")
        .build();
    var expectedBroadcast = Broadcast.builder()
        .channel(channel)
        .time(Instant.parse("2024-09-08T15:00:00.00Z"))
        .song(Song.builder()
            .artist(Artist.builder().name("artist").link("artistLink").build())
            .title(broadcast.title())
            .album(broadcast.album())
            .build())
        .build();

    // When
    List<Broadcast> broadcasts = new BroadcastMapper(instantFactory).map(channel, List.of(broadcast));

    // Then
    assertThat(broadcasts.get(0)).usingRecursiveComparison().isEqualTo(expectedBroadcast);
  }

  @Test
  void should_map_raw_break_to_broadcast() {
    // Given
    var channel = PredefinedChannel.DRONE_ZONE;
    var instantFactory = InstantFactory.fixed(Instant.parse("2024-09-08T16:00:00.00Z"));
    var rawBroadcast = RawBroadcast.builder().time("08:00:00").title("Break / Station ID").build();
    var expectedBroadcast = Broadcast.builder()
        .channel(channel)
        .time(Instant.parse("2024-09-08T15:00:00.00Z"))
        .song(Song.builder().title(rawBroadcast.title()).build())
        .build();

    // When
    List<Broadcast> broadcasts = new BroadcastMapper(instantFactory).map(channel, List.of(rawBroadcast));

    // Then
    assertThat(broadcasts.get(0)).usingRecursiveComparison().isEqualTo(expectedBroadcast);
  }

}
