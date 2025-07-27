package net.lecigne.somafm.recentlib;

import static net.lecigne.somafm.recentlib.SomaFm.BROADCAST_LOCATION;

import java.time.LocalTime;
import java.util.List;

/**
 * Convert a channel's recent broadcasts from their raw format to their domain format.
 */
class BroadcastMapper {

  private final InstantFactory instantFactory;

  BroadcastMapper(InstantFactory instantFactory) {
    this.instantFactory = instantFactory;
  }

  List<Broadcast> map(Channel channel, List<RawBroadcast> rawBroadcasts) {
    return rawBroadcasts
        .stream()
        .map(rawBroadcast -> Broadcast.builder()
            .time(TimeUtils.broadcastTimeToInstant(LocalTime.parse(rawBroadcast.time()), instantFactory.now(), BROADCAST_LOCATION))
            .channel(channel)
            .song(rawBroadcastToSong(rawBroadcast))
            .build())
        .toList();
  }

  private static Song rawBroadcastToSong(RawBroadcast rawBroadcast) {
    return Song.builder()
        .artist(mapArtist(rawBroadcast))
        .title(rawBroadcast.title())
        .album(rawBroadcast.album())
        .build();
  }

  private static Artist mapArtist(RawBroadcast rawBroadcast) {
    if (rawBroadcast.artist() == null && rawBroadcast.artistLink() == null) return null;
    return Artist.builder()
        .name(rawBroadcast.artist())
        .link(rawBroadcast.artistLink())
        .build();
  }

}
