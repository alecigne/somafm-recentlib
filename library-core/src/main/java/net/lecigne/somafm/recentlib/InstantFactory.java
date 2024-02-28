package net.lecigne.somafm.recentlib;

import java.time.Instant;

@FunctionalInterface
interface InstantFactory {
  Instant now();

  static InstantFactory system() {
    return Instant::now;
  }

  static InstantFactory fixed(Instant instant) {
    return () -> instant;
  }

}
