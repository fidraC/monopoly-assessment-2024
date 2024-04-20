package com.cm6123.monopoly.game;

/**
 * Unlike the real monopoly, the station cannot be bought. Players pay a fee when they land on it.
 */
public class StationSpace extends Space {
  /** The unit fee per distance traveled. */
  private final int fee;

  /** The name of the station. */
  private final String stationName;

  /**
   * Creates a new station space with a fee.
   *
   * @param unitFee the fee to be paid when a player lands on the space.
   */
  public StationSpace(final int unitFee) {
    this.fee = unitFee;
    // TODO: Use faker to generate a random station name.
    this.stationName = "Station";
  }

  /**
   * The player pays the unit fee × the sum of the last roll.
   *
   * @param player the player landing on the space.
   * @return the next action for the player.
   */
  @Override
  public NextAction action(final Player player) {
    final int sum = player.getLastRoll()[0] + player.getLastRoll()[1];
    if (player.deduct(fee * sum)) {
      return NextAction.END_TURN;
    } else {
      return NextAction.BANKRUPT;
    }
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
   * The name of the station.
   *
   * @return the name of the station.
   */
  @Override
  public String getName() {
    return stationName;
  }
}
