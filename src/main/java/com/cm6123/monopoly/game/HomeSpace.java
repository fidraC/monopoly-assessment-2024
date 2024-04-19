package com.cm6123.monopoly.game;

/** The home space extends the space class and gives the player $200 when they land on it. */
public class HomeSpace extends Space {

  private static final int HOME_SPACE_VALUE = 200;

  public HomeSpace() {}

  @Override
  public NextAction action(Player player) {
    player.add(HOME_SPACE_VALUE);
    return NextAction.END_TURN;
  }

  @Override
  boolean canBuy() {
    return false;
  }

  @Override
  boolean internalBuy(Player player) {
    return false;
  }

  @Override
  public String getName() {
    return "Home";
  }
}
