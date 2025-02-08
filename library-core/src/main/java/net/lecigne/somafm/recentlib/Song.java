package net.lecigne.somafm.recentlib;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Song {
  String artist;
  String title;
  String album;
}
