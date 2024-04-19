package com.cm6123.monopoly.game;

/**
 * A property is a space that can be bought by the player. A player landing on it that is not the
 * owner will have to pay rent.
 */
public class PropertySpace extends Space {
  private static final int RENT_PERCENTAGE = 10;

  private int value;
  private Player owner;

  public PropertySpace(int value) {
    this.value = value;
    this.owner = null;
  }

  private int getRent() {
    return this.value * RENT_PERCENTAGE / 100;
  }

  @Override
  public NextAction action(Player player) {
    if (this.owner != null && this.owner != player) {
      player.deduct(this.value);
      this.owner.add(this.getRent());
      return NextAction.END_TURN;
    } else {
      return NextAction.BUY;
    }
  }

  @Override
  boolean canBuy() {
    return (this.owner == null);
  }

  @Override
  protected boolean internalBuy(Player player) {
    if (player.deduct(this.value)) {
      this.owner = player;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String getName() {
    return "Property";
  }

  public Player getOwner() {
    return owner;
  }
}
