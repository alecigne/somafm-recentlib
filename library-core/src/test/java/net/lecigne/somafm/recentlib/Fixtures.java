package net.lecigne.somafm.recentlib;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

final class Fixtures {

  static List<Broadcast> getExpectedBroadcasts() {
    var vi = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(Instant.parse("2018-04-29T10:36:43Z"))
        .song(Song.builder().artist("Dirk Serries' Microphonics").title("VI").album("microphonics VI - XX").build())
        .build();
    var incandescentArc = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T12:26:56").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Igneous Flame").title("Incandescent Arc").album("Lapiz").build())
        .build();
    var furtherAfield = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T12:20:05").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Snufmumriko").title("Further Afield").album("This Tide Will Bring You Home").build())
        .build();
    var breakStationId = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T12:19:55").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("n/a").title("Break / Station ID").album("n/a").build())
        .build();
    var droneuary = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T12:11:07").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("xLWBxDRx").title("Droneuary XXXIII- Untitled").album("Droneuary 2019").build())
        .build();
    var maidenVoyage = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:59:39").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Ethereal Planes").title("Maiden Voyage").album("").build())
        .build();
    var approachingSectorV = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:51:24").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Transponder").title("Approaching Sector V").album("Ambient Online Compilation: Volume 9 (Part One)").build())
        .build();
    var awaken = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:42:56").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Loneward").title("Awaken").album("Protection").build())
        .build();
    var birdsLikeEarth = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:37:36").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Remote Vision").title("Birds Like Earth").album("Birds Like Earth").build())
        .build();
    var iceCave = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:35:07").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("The Dandelion Council").title("Ice Cave").album("Beyond Wist").build())
        .build();
    var day = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:31:14").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Dream Cycle").title("Day").album("").build())
        .build();
    var himmelszelt = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:14:12").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("klangdicht").title("Himmelszelt").album("Sternenstaub").build())
        .build();
    var serenity = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:04:58").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Howard Givens & Craig Padilla").title("Serenity, the Peaceful Place").album("The Bodhi Mantra").build())
        .build();
    var haveHeartWillTravel = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T11:00:10").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("The Adelaidean").title("Have Heart, Will Travel").album("Isolation").build())
        .build();
    var islanninKevat = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T10:55:50").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Arvik Torrenssen").title("Islannin Kevat").album("").build())
        .build();
    var strangeFields = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T10:50:02").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Ambient Alchemy").title("Strange Fields").album("She Arrived In A Dream").build())
        .build();
    var seagrass = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T10:44:08").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Isostatic").title("Seagrass (#676E51)").album("Earth Tones").build())
        .build();
    var roadToNowhere = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T10:39:59").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Alphaxone").title("Road to Nowhere").album("Edge of Solitude").build())
        .build();
    var nightspell = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T10:35:56").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Eivind Aarset and Jan Bang").title("Nightspell").album("Snow Catches on Her Eyelashes").build())
        .build();
    var elevations = Broadcast.builder()
        .channel(PredefinedChannel.DRONE_ZONE)
        .time(LocalDateTime.parse("2018-04-29T10:29:11").atZone(ZoneId.of("Europe/Paris")).toInstant())
        .song(Song.builder().artist("Robert Rich").title("Elevations").album("Tactile Ground").build())
        .build();
    return List.of(
        vi, incandescentArc, furtherAfield, breakStationId, droneuary,
        maidenVoyage, approachingSectorV, awaken, birdsLikeEarth, iceCave,
        day, himmelszelt, serenity, haveHeartWillTravel, islanninKevat,
        strangeFields, seagrass, roadToNowhere, nightspell, elevations
    );
  }

}
