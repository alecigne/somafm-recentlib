package net.lecigne.somafm.recentlib;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

/**
 * A broadcast: a song played at a specific time on a specific channel.
 */
@Builder
@Getter
public class Broadcast {
  private Channel channel;
  private Instant time;
  private Song song;
}
