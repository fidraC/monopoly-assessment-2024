package com.cm6123.monopoly.game;

/**
 * This is the overall class for the monopoly game. It only handles the game logic and not the user
 * interface. You must pass in an interface fulfilling the UserInterface interface.
 */
public class Monopoly {
  /** The board which handles spaces and player movement. */
  private final Board board;

  /** The user interface to use. */
  private final UserInterface ui;

  /**
   * Creates a new monopoly game with a fixed board size and a user interface.
   *
   * @param boardSize the size of the board.
   * @param userInterface the user interface to use.
   */
  public Monopoly(final int boardSize, final UserInterface userInterface) {
    this.ui = userInterface;
    // Prompt for the creation of players.
    this.ui.displayMessage("Welcome to monopoly", "bold");
    Player[] players = new Player[this.ui.getInteger("How many players are there?")];
    for (int i = 0; i < players.length; i++) {
      players[i] = new Player(this.ui.getString("Enter the name of player " + (i + 1)));
    }
    // Create the board.
    this.board = new Board(boardSize, players);
  }

  /** Starts the game loop. This will continue until the game is over. */
  public void gameLoop() {}

  /**
   * Get board (for testing).
   *
   * @return the board
   */
  public Board getBoard() {
    return board;
  }
}
