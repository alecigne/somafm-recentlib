package net.lecigne.somafm.example.adapters;

import lombok.Builder;
import net.lecigne.somafm.recentlib.Song;

@Builder
public record SongDto(ArtistDto artist, String title, String album) {
  static SongDto fromDomain(Song song) {
    return SongDto.builder()
        .artist(ArtistDto.fromDomain(song.artist()))
        .title(song.title())
        .album(song.album())
        .build();
  }
}
