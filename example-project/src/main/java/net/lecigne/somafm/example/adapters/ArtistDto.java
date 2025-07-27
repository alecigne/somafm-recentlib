package net.lecigne.somafm.example.adapters;

import lombok.Builder;
import net.lecigne.somafm.recentlib.Artist;

@Builder
public record ArtistDto(String name, String link) {
  static ArtistDto fromDomain(Artist artist) {
    if (artist == null) return null;
    return ArtistDto.builder()
        .name(artist.name())
        .link(artist.link())
        .build();
  }
}
