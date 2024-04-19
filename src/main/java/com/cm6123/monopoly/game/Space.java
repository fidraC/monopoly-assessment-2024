package com.cm6123.monopoly.game;

/**
 * The base class for a space on the board. It is abstract and should be extended by all other
 * spaces.
 */
public abstract class Space {

  /**
   * A space can make a player do something when they land on it. For example, the Home space gives
   * the player $200 while a rental property forces the player to pay rent.
   *
   * @param player the player landing on the space.
   * @return the next action for the player.
   */
  public abstract NextAction action(Player player);

  /**
   * A space can be bought by a player. A road cannot be owned, a property already owned cannot be
   * bought, etc
   *
   * @return true if the player can buy the space, false otherwise.
   */
  abstract boolean canBuy();

  /**
   * An internal method to be extended to buy the space. This will be called by the buy method if
   * the space can be bought.
   *
   * @param player the player buying the space.
   * @return true if the player bought the space, false otherwise.
   */
  boolean internalBuy(final Player player) {
    return false;
  }

  /**
   * The name of the space could be the type of space or the name of the property.
   *
   * @return the name of the space.
   */
  public abstract String getName();

  /**
   * Sets the owner of the space to the player if the property can be bought and is not already
   * owned.
   *
   * @param player the player buying the space.
   * @return true if the player bought the space, false otherwise.
   */
  public boolean buy(final Player player) {
    if (this.canBuy()) {
      return this.internalBuy(player);
    }
    return false;
  }
}
