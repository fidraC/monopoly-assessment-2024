package com.cm6123.monopoly.game;

import java.io.InputStream;
import java.util.Scanner;

/** This is the simple text-based implementation of the UserInterface. */
public class CommandLineInterface implements UserInterface {

  /** System input scanner. */
  private final Scanner scanner;

  /**
   * Constructs a new CommandLineInterface.
   *
   * @param in the input source for the scanner. Use stdin if null.
   */
  public CommandLineInterface(final InputStream in) {
    if (in == null) {
      this.scanner = new Scanner(System.in);
    } else {
      this.scanner = new Scanner(in);
    }
  }

  /**
   * Displays a message to the user. This implementation is incomplete and ignores options.
   *
   * @param message the message to display.
   * @param options optionals such as color and style
   */
  @Override
  public void displayMessage(final String message, final String... options) {
    System.out.println(message);
  }

  /**
   * Renders the board to the user. This implementation is incomplete and does nothing.
   *
   * @param board the board to render.
   */
  @Override
  public void renderBoard(final Board board) {
    // TODO: Render the board somehow
  }

  /**
   * Prompts the user for a choice from a list of choices. This implementation is incomplete and
   * always returns 0.
   *
   * @param choices the list of choices to choose from.
   * @return the index of the choice chosen.
   */
  @Override
  public Integer promptForChoice(final String[] choices) {
    for (int i = 0; i < choices.length; i++) {
      System.out.println(i + ": " + choices[i]);
    }
    System.out.print("Enter your choice: ");
    while (true) {
      try {
        String input = scanner.nextLine();
        int choice = Integer.parseInt(input);
        if (choice >= 0 && choice < choices.length) {
          return choice;
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice. Please try again.");
      }
      System.out.println("Invalid choice. Please try again.");
    }
  }

  /**
   * Gets a string from the user. This implementation is incomplete and always returns an empty
   * string.
   *
   * @param prompt the prompt to display to the user.
   * @return the string entered by the user.
   */
  @Override
  public String getString(final String prompt) {
    this.displayMessage(prompt);
    return scanner.nextLine();
  }

  /**
   * Gets an integer from the user. This implementation is incomplete and always returns 0.
   *
   * @param prompt the prompt to display to the user.
   * @return the integer entered by the user.
   */
  @Override
  public Integer getInteger(final String prompt) {
    this.displayMessage(prompt);
    String line = "";
    while (true) {
      try {
        line = this.scanner.nextLine();
        return Integer.parseInt(line);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please try again.");
        continue;
      }
    }
  }

  /**
   * Gets a double from the user. This implementation is incomplete and always returns 0.0.
   *
   * @param prompt the prompt to display to the user.
   * @return the double entered by the user.
   */
  @Override
  public Double getDouble(final String prompt) {
    this.displayMessage(prompt);
    String line = "";
    while (true) {
      try {
        line = this.scanner.nextLine();
        return Double.parseDouble(line);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please try again.");
        continue;
      }
    }
  }
}
