package net.lecigne.somafm.recentlib;

/**
 * A custom channel, useful when a SomaFM channel is not available as a {@link PredefinedChannel}.
 *
 * @param internalName The custom SomaFM channel's internal name, as found in the channel's URL.
 * @param publicName   The custom SomaFM channel's public name.
 * @param seasonal     The custom SomaFM channel's seasonal status.
 */
public record CustomChannel(String internalName, String publicName, boolean seasonal) implements Channel {

  public static CustomChannel of(String internalName, String publicName, boolean seasonal) {
    return new CustomChannel(internalName, publicName, seasonal);
  }

  @Override
  public String toString() {
    return publicName;
  }

}
