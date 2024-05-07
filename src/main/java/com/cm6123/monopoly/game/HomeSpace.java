package com.cm6123.monopoly.game;

/** The home space extends the space class and gives the player $200 when they land on it. */
public final class HomeSpace extends Space {

  /** The value of the home space. */
  private static final int HOME_SPACE_VALUE = 200;

  /** Create a new home space. */
  public HomeSpace() {}

  /** The player gets $200 when they land on the home space. */
  @Override
  public NextAction action(final Player player) {
    player.add(HOME_SPACE_VALUE);
    return NextAction.END_TURN;
  }

  /**
   * The name of the space is "Home". It really really really should be self explanatory I don't
   * know why checkstyle does this to me.
   *
   * @return the name of the space.
   */
  @Override
  public String getName() {
    return "Home";
  }
}
