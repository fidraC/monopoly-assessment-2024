package com.cm6123.monopoly.game;

import com.cm6123.monopoly.dice.Dice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** The player class represents a player in the game. */
public class Player {
  /** Logger for the Player class. */
  private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

  /** The starting balance for a player. */
  private static final int STARTING_BALANCE = 1000;

  /** The dice shared by all players. */
  private static final Dice DICE = new Dice(6);

  /** The name of the player. */
  private String name;

  /** The balance of the player. */
  private int balance;

  /** The last roll of the two dice for the player. */
  protected int[] lastRoll = new int[2];

  /**
   * Create a player with a name and a balance.
   *
   * @param playerName the name of the player.
   */
  public Player(final String playerName) {
    this.name = playerName;
    this.balance = STARTING_BALANCE;
  }

  /**
   * Deducts the amount from the player's balance.
   *
   * @param amount the amount to deduct.
   * @return true if the player had enough money to deduct the amount, false otherwise.
   */
  public boolean deduct(final int amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    }
    return false;
  }

  /**
   * Adds the amount to the player's balance.
   *
   * @param amount the amount to add.
   */
  public void add(final int amount) {
    balance += amount;
  }

  /**
   * Returns the player's balance.
   *
   * @return the player's balance.
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Returns the player's name.
   *
   * @return the player's name.
   */
  public String getName() {
    return name;
  }

  /** Rolls the dice for the player. */
  public void roll() {
    lastRoll[0] = DICE.roll();
    lastRoll[1] = DICE.roll();
  }

  /**
   * Returns the last roll of the dice.
   *
   * @return the last roll of the dice.
   */
  public int[] getLastRoll() {
    return lastRoll;
  }
}
