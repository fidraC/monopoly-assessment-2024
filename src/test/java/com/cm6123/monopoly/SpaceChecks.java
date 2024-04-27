package com.cm6123.monopoly;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cm6123.monopoly.game.HomeSpace;
import com.cm6123.monopoly.game.NextAction;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.PropertySpace;
import com.cm6123.monopoly.game.RoadSpace;
import com.cm6123.monopoly.game.StationSpace;
import com.cm6123.monopoly.game.TaxOfficeSpace;
import org.junit.jupiter.api.Test;

/** Tests for the Space class. */
public class SpaceChecks {
  class TestPlayer extends Player {
    private int[] lastRoll;

    public TestPlayer(int roll1, int roll2) {
      super("Test Player");
      this.lastRoll = new int[] {roll1, roll2};
    }

    @Override
    public int[] getLastRoll() {
      return lastRoll;
    }
  }

  @Test
  void testHomeSpace() {
    HomeSpace space = new HomeSpace();
    Player player = new Player("Test Player");
    int initialBalance = player.getBalance();
    space.action(player);
    assertTrue(player.getBalance() == initialBalance + 200);
  }

  @Test
  void testPropertyBuy() {
    // Test buying a property
    PropertySpace space = new PropertySpace(200);
    Player player = new Player("Test Player");
    int initialBalance = player.getBalance();
    assertTrue(space.action(player) == NextAction.BUY);
    assertTrue(space.buy(player));
    assertTrue(player.getBalance() == initialBalance - 200);
    assertTrue(space.getOwner() == player);
  }

  @Test
  void testUnableToBuyProperty() {
    Player player = new Player("Test Player");
    PropertySpace space = new PropertySpace(1001); // Player can't afford
    assertTrue(!space.buy(player));
    space = new PropertySpace(1000);
    assertTrue(space.buy(player));
    // Player can't buy a property twice
    assertTrue(!space.buy(player));
    // Player can't buy someone else's property
    Player player2 = new Player("Test Player 2");
    assertTrue(!space.buy(player2));
  }

  @Test
  void testPropertyRent() {
    Player player = new Player("Test Player");
    Player owner = new Player("Owner");
    PropertySpace space = new PropertySpace(200);
    space.buy(owner);
    int initialBalance = player.getBalance();
    int ownerInitialBalance = owner.getBalance();
    space.action(player);
    assertTrue(player.getBalance() == initialBalance - 20);
    assertTrue(owner.getBalance() == ownerInitialBalance + 20);
    // Test bankrupt
    player.deduct(player.getBalance());
    assertTrue(space.action(player) == NextAction.BANKRUPT);
    // Test owner is not charged if lands on own property
    initialBalance = owner.getBalance();
    space.action(owner);
    assertTrue(owner.getBalance() == initialBalance);
  }

  // I missed a bug where the player would be allowed to buy an already owned property. This is to
  // prevent regressions.
  @Test
  void testPropertyNextAction() {
    Player player = new Player("Test Player");
    Player owner = new Player("Owner");
    PropertySpace space = new PropertySpace(200);
    assertTrue(space.action(player) == NextAction.BUY);
    space.buy(owner);
    assertTrue(space.action(player) == NextAction.END_TURN);
    assertTrue(space.action(owner) == NextAction.END_TURN);
  }

  @Test
  void testTaxOfficeSpace() {

    Player player = new TestPlayer(4, 5);
    int initialBalance = player.getBalance();
    TaxOfficeSpace space = new TaxOfficeSpace();
    space.action(player);
    assertTrue(player.getBalance() == initialBalance - Math.ceil(initialBalance * 9 / 100.0));
    player = new TestPlayer(6, 6);
    space.action(player);
    assertTrue(player.getBalance() == initialBalance - Math.ceil(initialBalance * 6 / 100.0));
  }

  @Test
  void testStationFee() {
    Player player = new TestPlayer(1, 2);
    StationSpace space = new StationSpace(100);
    int initialBalance = player.getBalance();
    space.action(player);
    assertTrue(player.getBalance() == initialBalance - 300);
    player.deduct(player.getBalance());
    assertTrue(space.action(player) == NextAction.BANKRUPT);
  }

  @Test
  void testStationName() {
    StationSpace space = new StationSpace(100);
    assertTrue(space.getName().endsWith(" Station"));
    assertTrue(space.getName().length() > 9);
  }

  @Test
  void testPlayerRoll() {
    Player player = new Player("Test Player");
    player.roll();
    assertTrue(player.getLastRoll()[0] >= 1 && player.getLastRoll()[0] <= 6);
    assertTrue(player.getLastRoll()[1] >= 1 && player.getLastRoll()[1] <= 6);
  }

  @Test
  void testRoad() {
    RoadSpace space = new RoadSpace();
    Player player = new Player("Test Player");
    int initialBalance = player.getBalance();
    assertTrue(space.action(player) == NextAction.END_TURN);
    assertTrue(player.getBalance() == initialBalance);
  }
}
