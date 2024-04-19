package com.cm6123.monopoly.game;

/** The player class represents a player in the game. */
public class Player {
  /** The starting balance for a player. */
  private static final int STARTING_BALANCE = 1000;

  /** The name of the player. */
  private String name;

  /** The balance of the player. */
  private int balance;

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
}
