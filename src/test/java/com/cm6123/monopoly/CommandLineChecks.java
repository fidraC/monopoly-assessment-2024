package com.cm6123.monopoly;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cm6123.monopoly.game.CommandLineInterface;
import com.cm6123.monopoly.game.UserInterface;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/** This tests the parsing of the command line interface. */
public class CommandLineChecks {
  @Test
  void testChoice() {
    String input = "-1\n2\nhello\n0.3\n";
    InputStream buf = new ByteArrayInputStream(input.getBytes());
    UserInterface ui = new CommandLineInterface(buf);
    String[] choices = {"Choice 1", "Choice 2", "Choice 3"};
    assertEquals(ui.promptForChoice(choices), 2);
    assertEquals(ui.getDouble(""), 0.3);
  }
}
