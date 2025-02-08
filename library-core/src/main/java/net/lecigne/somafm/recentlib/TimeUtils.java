package net.lecigne.somafm.recentlib;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import lombok.experimental.UtilityClass;

@UtilityClass
class TimeUtils {

  /**
   * Convert a local broadcast time to a UTC {@link Instant}, assuming at most two days are
   * represented in the recent broadcasts view.
   * <p>
   * If the computed broadcast time is after the snapshot time, it is adjusted to the previous day.
   * <p>
   * Example:
   * <ul>
   *   <li>The track was broadcast at 22:25.</li>
   *   <li>A snapshot was taken on 2022-10-23 at 11:00 UTC.</li>
   *   <li>The local snapshot date in San Francisco was 2022-10-23 (04:00 UTC-7).</li>
   *   <li>A candidate UTC broadcast time is built from the date and time above: 2022-10-23 22:25
   *   UTC-7, which converts to 2022-10-24 05:25 UTC.</li>
   *   <li>This instant would be AFTER the UTC snapshot time, so the track must have been broadcast
   *   the day before, on 2022-10-23 at 05:25 UTC.</li>
   * </ul>
   */
  static Instant broadcastTimeToInstant(LocalTime broadcastTime, Instant snapshotTime, ZoneId broadcastLocation) {
    Instant candidateBroadcastUtc = broadcastTime
        .atDate(snapshotTime.atZone(broadcastLocation).toLocalDate())
        .atZone(broadcastLocation)
        .toInstant();
    return candidateBroadcastUtc.isAfter(snapshotTime)
        ? candidateBroadcastUtc.minus(1, DAYS)
        : candidateBroadcastUtc;
  }

}
