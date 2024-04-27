package com.cm6123.monopoly.game;

/**
 * The tax office space is a space that takes a percentage of the player's balance. The percentage
 * is the sum of the player's roll if it is not a double, otherwise it is the value of one of the
 * dice.
 */
public class TaxOfficeSpace extends MustPaySpace {

  /**
   * The tax office takes a percentage of the player's balance that is the sum of their roll, unless
   * it is a double. If it's a double, the percentage is the value of 1 dice.
   *
   * @param player the player landing on the space.
   * @return the next action for the player.
   */
  @Override
  public NextAction action(final Player player) {
    final int tax = getFee(player);
    if (player.deduct(tax)) {
      return NextAction.END_TURN;
    } else {
      return NextAction.BANKRUPT;
    }
  }

  /**
   * The fee for the tax office is based on the player's roll.
   *
   * @param player the player landing on the space
   * @return the fee for the tax office
   */
  @Override
  public int getFee(final Player player) {
    final int[] roll = player.getLastRoll();
    int taxPercentage;
    if (roll[0] == roll[1]) {
      taxPercentage = roll[0];
    } else {
      taxPercentage = roll[0] + roll[1];
    }
    return (int) Math.ceil(player.getBalance() * taxPercentage / 100.0);
  }

  /**
   * The tax office cannot be bought.
   *
   * @return false.
   */
  @Override
  boolean canBuy() {
    return false;
  }

  /**
   * The name of the space is "Tax Office".
   *
   * @return "Tax Office".
   */
  @Override
  public String getName() {
    return "Tax Office";
  }
}
