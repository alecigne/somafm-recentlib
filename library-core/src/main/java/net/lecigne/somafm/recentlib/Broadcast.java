package net.lecigne.somafm.recentlib;

import java.time.Instant;
import lombok.Builder;

/**
 * A broadcast: a song played at a specific time on a specific channel.
 */
@Builder
public record Broadcast(Channel channel, Instant time, Song song) {
}
