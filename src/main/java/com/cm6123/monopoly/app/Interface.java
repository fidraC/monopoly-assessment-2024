package com.cm6123.monopoly.app;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Interface class provides a simple text-based user interface for interacting with the game. It
 * allows for displaying messages, choices, and prompting for user input.
 */
public class Interface {

  /** Constructs a new Interface object with a Scanner object for reading user input. */
  public Interface() {}

  /**
   * Displays a message to the user.
   *
   * @param message The message to be displayed.
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  /**
   * Displays a choice builder with the given prompt and choices. Prompts the user to select a
   * choice and returns the chosen option.
   *
   * @param prompt The prompt message for the choice builder.
   * @return The user's chosen option, or -1 if an invalid choice is made.
   */
  public ChoiceBuilder choiceBuilder(String prompt) {
    ChoiceBuilder choiceBuilder = new ChoiceBuilder(prompt);
    System.out.println(prompt);
    return choiceBuilder;
  }

  /** An inner class that represents a choice in the choice builder. */
  private static class Choice {
    private int number;
    private String text;

    public Choice(int number, String text) {
      this.number = number;
      this.text = text;
    }

    public int getNumber() {
      return number;
    }

    public String getText() {
      return text;
    }
  }

  /** An inner class that represents a choice builder. */
  public static class ChoiceBuilder {
    private Scanner scanner;
    private ArrayList<Choice> choices;

    public ChoiceBuilder(String prompt) {
      this.choices = new ArrayList<>();
      this.scanner = new Scanner(System.in);
    }

    public ChoiceBuilder addChoice(String text, int number) {
      choices.add(new Choice(number, text));
      return this;
    }

    /** Prompts the user to select a choice and returns the chosen option. */
    public int prompt() {
      // Display the choices
      for (Choice choice : choices) {
        System.out.println(choice.getNumber() + ". " + choice.getText());
      }
      // Prompt the user for input of a choice
      System.out.print("Enter your choice: ");
      return scanner.nextInt();
    }
  }
}
