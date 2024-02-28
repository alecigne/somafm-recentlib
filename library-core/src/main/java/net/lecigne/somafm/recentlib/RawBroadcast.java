package net.lecigne.somafm.recentlib;

import lombok.Builder;

/**
 * A broadcast as displayed on SomaFm's "recently-played" pages.
 * <p>
 * This class stays as close as possible to the raw entry in the HTML page: fields are strings and
 * there is no channel-related data. This is basically a parsed entry.
 */
@Builder
record RawBroadcast(String time, String artist, String title, String album) {
}
