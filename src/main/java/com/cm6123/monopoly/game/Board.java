package com.cm6123.monopoly.game;

import java.lang.reflect.InvocationTargetException;

/**
 * The board is a collection of spaces that the players can move around. The board is a fixed size
 * and the spaces are in a fixed order.
 */
public class Board {

  /**
   * This is the pattern of spaces on the board. It repeats every 9 spaces with the first space
   * always being Home.
   */
  private static final Class<?>[] SPACE_PATTERN =
      new Class<?>[] {
        PropertySpace.class,
        PropertySpace.class,
        PropertySpace.class,
        RoadSpace.class,
        RoadSpace.class,
        TaxOfficeSpace.class,
        PropertySpace.class,
        PropertySpace.class,
        StationSpace.class
      };

  /** Contains an array of spaces that the players can move around. */
  private final Space[] spaces;

  /** The list of players on the board. */
  private final Player[] players;

  /** The position of each player on the board. */
  private final int[] playerPositions;

  /** The index of the current player. */
  private int currentPlayerIndex;

  /**
   * Creates a new board with a fixed size. We use a fixed pattern of spaces via the class
   * SPACE_PATTERN. It should not error unless a mistake was made by the developer (Missing blank
   * constructor, etc.)
   *
   * @param size the size of the board.
   * @param playerList the list of players on the board.
   */
  public Board(final int size, final Player[] playerList) {
    this.players = playerList;
    this.playerPositions = new int[playerList.length];
    this.spaces = new Space[size];
    this.spaces[0] = new HomeSpace();
    for (int i = 1; i < size; i++) {
      try {
        this.spaces[i] =
            (Space)
                SPACE_PATTERN[(i - 1) % SPACE_PATTERN.length]
                    .getDeclaredConstructor()
                    .newInstance();
      } catch (InstantiationException
          | IllegalAccessException
          | InvocationTargetException
          | NoSuchMethodException e) {
        e.printStackTrace();
        // Panic
        System.exit(1);
      }
    }
  }

  /**
   * Moves the player by the given number and returns the space they landed on. This allows the game
   * object to run action on the player.
   *
   * @param number the number of spaces to move the player.
   * @return the space the player landed on.
   */
  public int movePlayer(final int number) {
    // If the player has looped around BUT not landed on the HomeSpace, we need to run
    // the action of HomeSpace (collect money).
    if (this.playerPositions[this.currentPlayerIndex] + number > this.spaces.length) {
      this.spaces[0].action(this.players[this.currentPlayerIndex]);
    }
    // Wrap around the board
    this.playerPositions[this.currentPlayerIndex] =
        (this.playerPositions[this.currentPlayerIndex] + number) % this.spaces.length;
    return this.playerPositions[this.currentPlayerIndex];
  }

  /**
   * Gets the current player.
   *
   * @return the current player.
   */
  public Player getCurrentPlayer() {
    return this.players[this.currentPlayerIndex];
  }

  /**
   * End the turn of the current player and move to the next player.
   *
   * @return whether the game is over.
   */
  public boolean endTurn() {
    this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.length;
    var backupIndex = this.currentPlayerIndex;
    // Use a counter to prevent infinite loops
    int counter = 0;
    while (!this.players[this.currentPlayerIndex].isInGame() && counter < this.players.length) {
      this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.length;
      counter++;
    }
    return this.currentPlayerIndex == backupIndex;
  }

  /**
   * Gets the space at the given index.
   *
   * @param index the index of the space.
   * @return the space at the index.
   */
  public Space getSpace(final int index) {
    return this.spaces[index];
  }
}
