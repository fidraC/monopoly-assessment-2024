package com.cm6123.monopoly;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.PropertySpace;
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
}
