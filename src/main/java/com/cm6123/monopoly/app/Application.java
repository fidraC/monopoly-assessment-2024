package com.cm6123.monopoly.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the main class for the monopoly game. It is purely a wrapper for the Monopoly game and
 * the interface.
 */
public final class Application {
  /** Create a logger for the class. */
  private static Logger logger = LoggerFactory.getLogger(Application.class);

  private Application() {}

  /**
   * main entry point. If args provided, result is displayed and program exists. Otherwise, user is
   * prompted for input.
   *
   * @param args command line args.
   */
  public static void main(final String[] args) {

    logger.info("Application Started with args:{}", String.join(",", args));

    UserInterface ui = new CommandLineInterface(System.in);

    var boardSize = ui.getInteger("Enter the size of the board");
    if (boardSize > 50 || boardSize < 10) {
      ui.displayMessage("Board size must be between 10 and 50");
      return;
    }

    var monopoly = new Monopoly(boardSize, ui);
    monopoly.gameLoop();

    logger.info("Application closing");
  }
}
