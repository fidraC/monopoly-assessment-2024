package com.cm6123.monopoly.game;

/** A road space is a no-op space. It does nothing. */
public class RoadSpace extends Space {
  /** Creates a new road space. */
  public RoadSpace() {}

  /**
   * Does nothing.
   *
   * @param player the player landing on the space.
   * @return the next action for the player.
   */
  @Override
  public NextAction action(final Player player) {
    return NextAction.END_TURN;
  }

  /**
   * Cannot be bought.
   *
   * @return false
   */
  @Override
  boolean canBuy() {
    return false;
  }

  /**
   * The name of the road.
   *
   * @return the name of the road.
   */
  @Override
  public String getName() {
    return "Road";
  }
}
