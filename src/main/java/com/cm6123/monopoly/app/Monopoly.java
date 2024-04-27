package com.cm6123.monopoly.app;

import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.MustPaySpace;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.PropertySpace;

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
    this.board = new Board(boardSize);
    this.ui.renderBoard(board);
    // Prompt for the creation of players.
    Player[] players = new Player[this.ui.getInteger("How many players are there?")];
    for (int i = 0; i < players.length; i++) {
      players[i] = new Player(this.ui.getString("Enter the name of player " + (i + 1)));
    }
    this.board.setPlayers(players);
  }

  /** Starts the game loop. This will continue until the game is over. */
  public void gameLoop() {
    while (true) {
      final var player = this.board.getCurrentPlayer();
      this.ui.displayMessage("It is " + player.getName() + "'s turn.");
      // Show player info
      player.roll();
      var roll = player.getLastRoll();
      this.ui.displayMessage("You rolled a " + roll[0] + " and a " + roll[1]);
      var space = this.board.getSpace(this.board.movePlayer(roll[0] + roll[1]));
      this.ui.displayMessage("You landed on " + space.getName());
      var nextAction = space.action(player);
      int debts = 0;
      this.ui.displayMessage("You have $" + player.getBalance());
      if (space instanceof PropertySpace) {
        var property = (PropertySpace) space;
        if (property.getOwner() != null) {
          this.ui.displayMessage("This property is owned by " + property.getOwner().getName());
          this.ui.displayMessage("You must pay rent of $" + property.getRent());
          debts = property.getRent();
        }
      } else if (space instanceof MustPaySpace) {
        this.ui.displayMessage("You must pay $" + ((MustPaySpace) space).getFee(player));
        debts = ((MustPaySpace) space).getFee(player);
      }
      switch (nextAction) {
        case END_TURN:
          break;
        case BUY:
          if (player.getBalance() < ((PropertySpace) space).getValue()) {
            break;
          }
          this.ui.displayMessage(
              "Would you like to buy "
                  + space.getName()
                  + " for $"
                  + ((PropertySpace) space).getValue()
                  + "?");
          if (this.ui.promptForChoice(new String[] {"No", "Yes"}) == 1) {
            if (space.buy(player)) {
              this.ui.displayMessage("You bought " + space.getName());
            } else {
              this.ui.displayMessage(
                  "Failed to buy " + space.getName() + ". Do you have enough money?");
            }
          }
          break;
        case BANKRUPT:
          this.ui.displayMessage(
              "You are bankrupt! Properties have been sold off to pay your debts.");
          if (!player.bankrupt(debts)) {
            this.ui.displayMessage("You are out of the game!");
          }
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + nextAction);
      }
      if (this.board.endTurn()) {
        this.ui.displayMessage("Game over! You won!");
        break;
      }
      this.ui.getString("Press enter to continue.");
    }
  }

  /**
   * Get board (for testing).
   *
   * @return the board
   */
  public Board getBoard() {
    return board;
  }
}
