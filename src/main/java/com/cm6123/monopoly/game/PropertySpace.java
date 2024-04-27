package com.cm6123.monopoly.game;

import com.github.javafaker.Faker;
import java.util.Locale;

/**
 * A property is a space that can be bought by the player. A player landing on it that is not the
 * owner will have to pay rent.
 */
public final class PropertySpace extends Space {
  /** Temporary rent percentage. */
  private static final int RENT_PERCENTAGE = 10;

  /** The minimum value of the property. */
  private static final int MIN_VALUE = 100;

  /** The maximum value of the property. */
  private static final int MAX_VALUE = 500;

  /** The value of the property. */
  private int value;

  /** The owner of the property. Can be null. */
  private Player owner;

  /** The name of the property. */
  private final String propertyName;

  /**
   * Create a new property space with a value.
   *
   * @param val the value of the property.
   */
  public PropertySpace(final int val) {
    this.value = val;
    this.owner = null;
    Faker faker = new Faker(new Locale.Builder().setLanguage("en").setRegion("GB").build());
    this.propertyName = faker.address().streetName();
  }

  /** Create a property space with a random value. */
  public PropertySpace() {
    this(new Faker().number().numberBetween(MIN_VALUE, MAX_VALUE));
  }

  /**
   * Get the value of the property.
   *
   * @return the value of the property.
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Get the rent value of the property.
   *
   * @return the rent value.
   */
  public int getRent() {
    return this.value * RENT_PERCENTAGE / 100;
  }

  /**
   * The player pays rent if the property is owned by another player. If the property is not owned,
   * the player can buy it.
   *
   * @param player the player landing on the property.
   * @return the next action for the player.
   */
  @Override
  public NextAction action(final Player player) {
    if (this.owner != null && this.owner != player) {
      this.owner.add(this.getRent());
      if (player.deduct(this.getRent())) {
        return NextAction.END_TURN;
      } else {
        return NextAction.BANKRUPT;
      }
    } else if (this.owner == null) {
      return NextAction.BUY;
    }
    return NextAction.END_TURN;
  }

  /**
   * The player can buy the property if it is not already owned.
   *
   * @return true if the property can be bought, false otherwise.
   */
  @Override
  boolean canBuy() {
    return (this.owner == null);
  }

  /**
   * Internal method to buy the property.
   *
   * @param player the player buying the property.
   * @return true if the player bought the property, false otherwise.
   */
  @Override
  protected boolean internalBuy(final Player player) {
    if (player.deduct(this.value)) {
      this.owner = player;
      this.owner.addProperty(this);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get the name of the property.
   *
   * @return the name of the property.
   */
  @Override
  public String getName() {
    return this.propertyName;
  }

  /**
   * Get the owner of the property.
   *
   * @return the owner of the property.
   */
  public Player getOwner() {
    return owner;
  }

  /** Remove the owner of the property. */
  public void removeOwner() {
    this.owner = null;
  }
}
