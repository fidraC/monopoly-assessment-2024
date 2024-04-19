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
    PropertySpace space = new PropertySpace(200);
    Player player = new Player("Test Player");
    int initialBalance = player.getBalance();
    assertTrue(space.action(player) == NextAction.BUY);
    assertTrue(space.buy(player));
    assertTrue(player.getBalance() == initialBalance - 200);
  }
}
