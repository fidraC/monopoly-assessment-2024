package com.cm6123.monopoly.game;

/** A space where the player is required to pay an amount to the bank. */
public abstract class MustPaySpace extends Space {
  /**
   * Returns the fee that the player must pay.
   *
   * @param player the player landing on the space.
   * @return the fee that the player must pay.
   */
  public abstract int getFee(Player player);
}
