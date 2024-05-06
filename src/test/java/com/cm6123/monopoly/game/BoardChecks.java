package com.cm6123.monopoly.game;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests for the Board class. */
public class BoardChecks {
  @Test
  void testBoardSpaces() {
    Player player = new Player("Test Player");
    Board board = new Board(50, new Player[] {player});
    assertTrue(board.getSpace(0).getName().equals("Home"));
    assertTrue(board.getSpace(9).getName().endsWith("Station"));
    assertTrue(board.getSpace(49).getName().endsWith("Station"));
  }

  @Test
  void testMovePlayer() {
    Player player = new Player("Test Player");
    Player player2 = new Player("Test Player 2");
    Board board = new Board(50, new Player[] {player, player2});
    assertTrue(board.movePlayer(5) == 5);
    assertTrue(board.movePlayer(6) == 11);
    assertTrue(board.getSpace(11).action(board.getCurrentPlayer()) == NextAction.BUY);
    board.endTurn();
    assertTrue(board.getCurrentPlayer() == player2);
    // Wraparound
    board.endTurn();
    assertTrue(board.getCurrentPlayer() == player);
  }

  @Test
  void testBankruptPlayer() {
    Player player = new Player("Test Player");
    Player player2 = new Player("Test Player 2");
    player2.deduct(player2.getBalance());
    player2.bankrupt(1);
    Board board = new Board(50, new Player[] {player, player2});
    assertTrue(board.endTurn());
  }
}
