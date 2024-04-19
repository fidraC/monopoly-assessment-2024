package com.cm6123.monopoly.game;

/** The player class represents a player in the game. */
public class Player {
  private static final int STARTING_BALANCE = 1000;
  private String name;
  private int balance;

  /**
   * Create a player with a name and a balance.
   *
   * @param name the name of the player.
   */
  public Player(String name) {
    this.name = name;
    this.balance = STARTING_BALANCE;
  }

  /** Deducts the amount from the player's balance. */
  public boolean deduct(int amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    }
    return false;
  }

  /** Adds the amount to the player's balance. */
  public void add(int amount) {
    balance += amount;
  }

  public int getBalance() {
    return balance;
  }

  public String getName() {
    return name;
  }
}
