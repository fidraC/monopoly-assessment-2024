package com.cm6123.monopoly.app;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * This is the main class for the monopoly game. It is purely a wrapper for the Monopoly game and
 * the interface.
 */
public final class Application {
  /** Create a logger for the class. */
  private Application() {}

  /**
   * main entry point. If args provided, result is displayed and program exists. Otherwise, user is
   * prompted for input.
   *
   * @param args command line args.
   */
  public static void main(final String[] args) {

    Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.DEBUG);

    UserInterface ui = new CommandLineInterface(System.in);

    var boardSize = ui.getInteger("Enter the size of the board");
    if (boardSize > 50 || boardSize < 10) {
      ui.displayMessage("Board size must be between 10 and 50");
      return;
    }

    var monopoly = new Monopoly(boardSize, ui);
    monopoly.gameLoop();
  }
}
