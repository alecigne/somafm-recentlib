package net.lecigne.somafm.recentlib;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * A SomaFM channel.
 */
public enum PredefinedChannel implements Channel {
  BEAT_BLENDER("beatblender", "Beat Blender", false),
  BLACK_ROCK_FM("brfm", "Black Rock FM", false),
  BOOT_LIQUOR("bootliquor", "Boot Liquor", false),
  BOSSA_BEYOND("bossa", "Bossa Beyond", false),
  CHRISTMAS_LOUNGE("christmas", "Christmas Lounge", true),
  CHRISTMAS_ROCKS("xmasrocks", "Christmas Rocks!", true),
  CLIQHOP_IDM("cliqhop", "cliqhop idm", false),
  COVERS("covers", "Covers", false),
  DEEP_SPACE_ONE("deepspaceone", "Deep Space One", false),
  DEF_CON_RADIO("defcon", "DEF CON Radio", false),
  DIGITALIS("digitalis", "Digitalis", false),
  DOOMED("doomed", "Doomed", false),
  DRONE_ZONE("dronezone", "Drone Zone", false),
  DUB_STEP_BEYOND("dubstep", "Dub Step Beyond", false),
  FLUID("fluid", "Fluid", false),
  FOLK_FORWARD("folkfwd", "Folk Forward", false),
  GROOVE_SALAD("groovesalad", "Groove Salad", false),
  GROOVE_SALAD_CLASSICS("gsclassic", "Groove Salad Classics", false),
  HEAVYWEIGHT_REGGAE("reggae", "Heavyweight Reggae", false),
  ILLINOIS_STREET_LOUNGE("illstreet", "Illinois Street Lounge", false),
  INDIE_POP_ROCKS("indiepop", "Indie Pop Rocks!", false),
  JOLLY_OL_SOUL("jollysoul", "Jolly Ol' Soul", true),
  LEFT_COAST_70S("seventies", "Left Coast 70s", false),
  LUSH("lush", "Lush", false),
  METAL_DETECTOR("metal", "Metal Detector", false),
  MISSION_CONTROL("missioncontrol", "Mission Control", false),
  N5MD_RADIO("n5md", "n5MD Radio", false),
  POPTRON("poptron", "PopTron", false),
  SECRET_AGENT("secretagent", "Secret Agent", false),
  SEVEN_INCH_SOUL("7soul", "Seven Inch Soul", false),
  SF_10_33("sf1033", "SF 10-33", false),
  SF_IN_SF("sfinsf", "SF in SF", false),
  SF_POLICE_SCANNER("scanner", "SF Police Scanner", false),
  SOMA_FM_LIVE("live", "SomaFM Live", false),
  SOMA_FM_SPECIALS("specials", "SomaFM Specials", false),
  SONIC_UNIVERSE("sonicuniverse", "Sonic Universe", false),
  SPACE_STATION_SOMA("spacestation", "Space Station Soma", false),
  SUBURBS_OF_GOA("suburbsofgoa", "Suburbs of Goa", false),
  SYNPHAERA_RADIO("synphaera", "Synphaera Radio", false),
  THE_DARK_ZONE("darkzone", "The Dark Zone", false),
  THE_IN_SOUND("insound", "The In-Sound", false),
  THE_TRIP("thetrip", "The Trip", false),
  THISTLE_RADIO("thistle", "ThistleRadio", false),
  TIKI_TIME("tikitime", "Tiki Time", false),
  UNDERGROUND_80S("u80s", "Underground 80s", false),
  VAPORWAVES("vaporwaves", "Vaporwaves", false),
  XMAS_IN_FRISKO("xmasinfrisko", "Xmas in Frisko", true);

  /**
   * The SomaFM channel's internal name, as found in the channel's URL.
   */
  private final String internalName;

  /**
   * The SomaFM channel's public name.
   */
  private final String publicName;

  /**
   * The SomaFM channel's seasonal status.
   */
  private final boolean seasonal;

  PredefinedChannel(String internalName, String publicName, boolean seasonal) {
    this.internalName = internalName;
    this.publicName = publicName;
    this.seasonal = seasonal;
  }

  public static Optional<PredefinedChannel> getByInternalName(String internalName) {
    return getBy(channel -> channel.internalName.equals(internalName));
  }

  public static Optional<PredefinedChannel> getByPublicName(String publicName) {
    return getBy(channel -> channel.publicName.equals(publicName));
  }

  private static Optional<PredefinedChannel> getBy(Predicate<PredefinedChannel> predicate) {
    return Arrays.stream(PredefinedChannel.values()).filter(predicate).findFirst();
  }

  @Override
  public String internalName() {
    return internalName;
  }

  @Override
  public String publicName() {
    return publicName;
  }

  @Override
  public boolean seasonal() {
    return seasonal;
  }

  @Override
  public String toString() {
    return publicName;
  }

}
