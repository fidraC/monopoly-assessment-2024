package com.cm6123.monopoly.app;

import com.cm6123.monopoly.game.Board;

/**
 * This is the generic interface for implementing a UI for user interaction. This is to allow easy
 * testing and future expansion from a TUI to GUI,
 */
interface UserInterface {
  /**
   * Displays a message to the user.
   *
   * @param message the message to display.
   * @param options optionals such as color and style
   */
  void displayMessage(String message, String... options);

  /**
   * Renders the board to the user.
   *
   * @param board the board to render.
   */
  void renderBoard(Board board);

  /**
   * Prompts the user for a choice from a list of choices.
   *
   * @param choices the list of choices to choose from.
   * @return the index of the choice chosen.
   */
  Integer promptForChoice(String[] choices);

  /**
   * Gets a string from the user.
   *
   * @param prompt the prompt to display to the user.
   * @return the string entered by the user.
   */
  String getString(String prompt);

  /**
   * Gets an integer from the user.
   *
   * @param prompt the prompt to display to the user.
   * @return the integer entered by the user.
   */
  Integer getInteger(String prompt);

  /**
   * Gets a double from the user.
   *
   * @param prompt the prompt to display to the user.
   * @return the double entered by the user.
   */
  Double getDouble(String prompt);
}
