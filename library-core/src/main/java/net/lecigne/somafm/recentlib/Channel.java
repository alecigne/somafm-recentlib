package net.lecigne.somafm.recentlib;

import java.net.URI;

public interface Channel {
  String internalName();
  String publicName();
  boolean seasonal();

  /**
   * Create the relative path to the channel's recently played tracks HTML page.
   */
  default URI toRecentPath() {
    return URI.create(String.format("recent/%s.%s", internalName(), "html"));
  }

}
