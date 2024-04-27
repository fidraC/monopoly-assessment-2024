package com.cm6123.monopoly;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cm6123.monopoly.app.CommandLineInterface;
import com.cm6123.monopoly.app.UserInterface;
import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Player;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

  @ParameterizedTest
  @ValueSource(ints = {10, 24, 35, 41, 48, 50})
  void testBoardRendering(int size) {
    Board board = new Board(size, new Player[0]);
    UserInterface ui = new CommandLineInterface(null);
    ui.renderBoard(board);
  }
}
