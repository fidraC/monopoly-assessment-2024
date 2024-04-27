package com.cm6123.monopoly.game;

import com.cm6123.monopoly.dice.Dice;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** The player class represents a player in the game. */
public class Player {
  /** The logger for the player class. */
  private static final Logger LOGGER = LogManager.getLogger(Player.class);

  /** The starting balance for a player. */
  private static final int STARTING_BALANCE = 1000;

  /** The dice shared by all players. */
  private static final Dice DICE = new Dice(6);

  /** The name of the player. */
  private String name;

  /** The balance of the player. */
  private int balance;

  /** The last roll of the two dice for the player. */
  private int[] lastRoll = new int[2];

  /** Whether the player is still in the game. */
  private boolean inGame = true;

  /** The properties owned by the player. */
  private List<PropertySpace> properties;

  /**
   * Create a player with a name and a balance.
   *
   * @param playerName the name of the player.
   */
  public Player(final String playerName) {
    this.name = playerName;
    this.balance = STARTING_BALANCE;
    this.properties = new ArrayList<PropertySpace>();
  }

  /**
   * We keep track of the properties owned by the player to sell when the player goes bankrupt. We
   * always keep the properties sorted by value. By using binary search, we can find the insertion
   * index in O(log n) time.
   *
   * @param property the property to add to the player's list of properties.
   */
  protected void addProperty(final PropertySpace property) {
    // Use binary search to find the insertion index
    int low = 0;
    int high = properties.size() - 1;
    int insertionIndex = properties.size();

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (properties.get(mid).getValue() < property.getValue()) {
        low = mid + 1;
      } else {
        insertionIndex = mid;
        high = mid - 1;
      }
    }

    properties.add(insertionIndex, property);
    LOGGER.info(property.getName() + " successfully bought by player: " + property.getName());
  }

  /**
   * Bankrupt the player. This will sell the player's properties from the least expensive to the
   * most expensive until the player has enough money to pay off their debts.
   *
   * @param debts the amount of money the player owes.
   * @return whether the player was able to pay off their debt.
   */
  public boolean bankrupt(final int debts) {
    var debt = debts;
    while (debt > 0 && properties.size() > 0) {
      PropertySpace property = properties.remove(0);
      property.removeOwner();
      LOGGER.info(property.getName() + " sold due to bankruptcy by player: " + property.getName());
      // We sell the property for half its value.
      var val = property.getValue() / 2;
      debt -= val;
      if (debt < 0) {
        // We add the remaining value to the player's balance.
        balance += Math.abs(debt);
        break;
      }
    }
    this.inGame = debt == 0;
    return inGame;
  }

  /**
   * Returns whether the player is still in the game.
   *
   * @return whether the player is still in the game.
   */
  public boolean isInGame() {
    return inGame;
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
      LOGGER.info("Player: " + name + " paid " + amount + " and now has " + balance);
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
    LOGGER.info("Player: " + name + " received " + amount + " and now has " + balance);
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
