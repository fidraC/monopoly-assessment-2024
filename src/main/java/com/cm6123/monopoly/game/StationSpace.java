package com.cm6123.monopoly.game;

import com.github.javafaker.Faker;
import java.util.Locale;

/**
 * Unlike the real monopoly, the station cannot be bought. Players pay a fee when they land on it.
 */
public class StationSpace extends MustPaySpace {
  /** The unit fee per distance traveled. */
  private final int fee;

  /** The name of the station. */
  private final String stationName;

  /** The minimum unit fee of the station. */
  private static final int MIN_UNIT_FEE = 10;

  /**
   * The maximum unit fee of the station. This is made to try and match the maximum rent of a
   * property (50)
   */
  private static final int MAX_UNIT_FEE = 20;

  /**
   * Creates a new station space with a fee.
   *
   * @param unitFee the fee to be paid when a player lands on the space.
   */
  public StationSpace(final int unitFee) {
    this.fee = unitFee;
    Faker faker = new Faker(new Locale.Builder().setLanguage("en").setRegion("GB").build());
    this.stationName = faker.harryPotter().spell();
  }

  /** Creates a new station space with a random fee. */
  public StationSpace() {
    this(new Faker().number().numberBetween(MIN_UNIT_FEE, MAX_UNIT_FEE));
  }

  /**
   * The player pays the unit fee × the sum of the last roll.
   *
   * @param player the player landing on the space.
   * @return the next action for the player.
   */
  @Override
  public NextAction action(final Player player) {
    final int totalFee = getFee(player);
    if (player.deduct(totalFee)) {
      return NextAction.END_TURN;
    } else {
      return NextAction.BANKRUPT;
    }
  }

  /**
   * Returns the fee that the player must pay.
   *
   * @param player the player landing on the space.
   * @return the fee that the player must pay.
   */
  @Override
  public int getFee(final Player player) {
    final int sum = player.getLastRoll()[0] + player.getLastRoll()[1];
    return fee * sum;
  }

  /**
   * The name of the station.
   *
   * @return the name of the station.
   */
  @Override
  public String getName() {
    return stationName + " Station";
  }
}
