package com.cm6123.monopoly.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests for the Player class. */
public class PlayerChecks {
  @Test
  void testBankruptcy() {
    Player player = new Player("Test Player");
    assertTrue(player.deduct(1000));
    assertFalse(player.deduct(1));
    player.add(200);

    PropertySpace space = new PropertySpace(200);
    space.buy(player);

    assertTrue(player.bankrupt(100));
    assertTrue(space.getOwner() == null);
  }

  @Test
  void testAddPropertySort() {
    Player player = new Player("Test Player");
    PropertySpace property2 = new PropertySpace(200);
    PropertySpace property3 = new PropertySpace(300);
    PropertySpace property4 = new PropertySpace(400);
    property3.buy(player);
    property4.buy(player);
    PropertySpace property1 = new PropertySpace(100);
    property2.buy(player);
    property1.buy(player);

    assertTrue(player.bankrupt(100));
    // Sold for 50
    assertTrue(property1.getOwner() == null);
    // Sold for 100 (player now has 50 extra)
    assertTrue(property2.getOwner() == null);
    assertTrue(property3.getOwner() == player);
    // Player starts with 1000, uses up 100, 200, 300, 400 = 1000 so has 0 left
    assertTrue(player.getBalance() == 50);
  }
}
