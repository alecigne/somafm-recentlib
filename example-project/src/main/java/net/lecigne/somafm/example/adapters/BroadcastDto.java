package net.lecigne.somafm.example.adapters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Builder;
import net.lecigne.somafm.recentlib.Broadcast;

@Builder
record BroadcastDto(
    String utcTime,
    String channel,
    SongDto song
) {

  static BroadcastDto fromDomain(Broadcast broadcast) {
    LocalDateTime localDateTime = broadcast.time().atZone(ZoneId.systemDefault()).toLocalDateTime();
    return BroadcastDto.builder()
        .utcTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime))
        .channel(broadcast.channel().publicName())
        .song(SongDto.fromDomain(broadcast.song()))
        .build();
  }

  static List<BroadcastDto> fromDomain(List<Broadcast> broadcasts) {
    return broadcasts.stream().map(BroadcastDto::fromDomain).toList();
  }

}
