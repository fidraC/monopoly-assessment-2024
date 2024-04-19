package com.cm6123.monopoly;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cm6123.monopoly.game.HomeSpace;
import com.cm6123.monopoly.game.NextAction;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.PropertySpace;
import org.junit.jupiter.api.Test;

/** Tests for the Space class. */
public class SpaceChecks {
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
  }
}
