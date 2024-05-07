package com.cm6123.monopoly.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/** Tests for the Monopoly class. */
public class MonopolyChecks {
  @Test
  void testMonopolyInitialization() {
    String input = "2\nPlayer 1\nPlayer 2\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    CommandLineInterface ui = new CommandLineInterface(in);
    Monopoly game = new Monopoly(10, ui);
    var board = game.getBoard();
    board.endTurn();
    board.endTurn();
    // This should now be back to player 1
    assertEquals(board.getCurrentPlayer().getName(), "Player 1");
  }
}
