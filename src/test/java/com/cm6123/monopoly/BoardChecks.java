package com.cm6123.monopoly;

import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Space;
import com.cm6123.monopoly.game.Spaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class BoardChecks {
	@ParameterizedTest
	@ValueSource(ints = {10,20,30,40,50})
	public void boardHasAtLeastOneOfEachSpace(Integer size) throws Exception {
		Board board = new Board(size);
		assertTrue(board.space(0).getType() == Spaces.HOME);
		Map<Spaces, Boolean> spaceCount = new HashMap<>();
		Space[] spaces = board.spaces();
		for (int i = 1; i < spaces.length; i++){
			Boolean b = true;
			spaceCount.put(spaces[i].getType(), b);
		}
		assertTrue(spaceCount.keySet().toArray().length == 5);
	}
}
