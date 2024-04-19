package com.cm6123.monopoly.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The base class for a space on the board. It is abstract and should be extended by all other
 * spaces.
 */
public abstract class Space {
  private static Logger logger = LoggerFactory.getLogger(Space.class);

  /**
   * A space can make a player do something when they land on it. For example, the Home space gives
   * the player $200 while a rental property forces the player to pay rent.
   */
  public abstract NextAction action(Player player);

  /**
   * A space can be bought by a player. A road cannot be owned, a property already owned cannot be
   * bought, etc
   *
   * @return true if the player can buy the space, false otherwise.
   */
  abstract boolean canBuy();

  abstract boolean internalBuy(Player player);

  /** The name of the space could be the type of space or the name of the property. */
  public abstract String getName();

  /**
   * Sets the owner of the space to the player if the property can be bought and is not already
   * owned.
   */
  public boolean buy(Player player) {
    if (this.canBuy()) {
      return this.internalBuy(player);
    }
    logger.error("Player cannot buy this space");
    return false;
  }
}
