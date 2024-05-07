package com.cm6123.monopoly.dice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Pre-made dice checks by lecturer. */
public class DiceChecks {

  @ParameterizedTest
  @ValueSource(ints = {4, 6, 7, 8, 10, 12, 15})
  void diceNeverExceedsNumberOfFacesOrGoesBelowOne(Integer faces) throws Exception {

    Integer min = 1;
    Integer max = faces;

    Dice dice = new Dice(faces);

    for (int count = 0; count < 1000; count++) {
      Integer roll = dice.roll();
      min = min < roll ? min : roll;
      max = max > roll ? max : roll;
    }

    final Integer lastMax = max;
    final Integer lastMin = min;

    // NOTE: Java autoboxing rules will catch you here! a==b will compare references. a == 1 will
    // unbox and compare values!!!

    Assertions.assertAll(
        () ->
            assertTrue(
                lastMax.equals(faces),
                "max doesn't equal number of faces, but actually equals " + lastMax),
        () -> assertTrue(lastMin.equals(1), "min does't equal 1, but actually equals " + lastMin));
  }

  @ParameterizedTest
  @ValueSource(ints = {4, 6, 8, 10, 12})
  void diceRollsEveryValueAtLeastOnce(Integer faces) throws Exception {

    Set<Integer> values = new HashSet<>();

    Dice dice = new Dice(faces);

    for (int count = 0; count < 1000; count++) {
      values.add(dice.roll());
    }

    assertTrue(values.size() == faces);
  }

  @ParameterizedTest
  @ValueSource(ints = {4, 6, 8, 10, 12})
  void diceRollsEveryValueRoughlyEqually(Integer faces) throws Exception {
    Integer rolls = 1000;

    Map<Integer, Integer> valueCount = new HashMap<>();

    Dice dice = new Dice(faces);

    for (int count = 0; count < rolls; count++) {
      Integer roll = dice.roll();
      valueCount.put(roll, valueCount.getOrDefault(roll, 0) + 1);
    }

    for (Integer eachFace = 1; eachFace <= faces; eachFace++) {
      assertTrue(valueCount.get(eachFace) >= rolls / (faces * 2));
    }
  }

  @Test
  void rollOnce() {
    Dice dice = new Dice(6);
    Integer roll = dice.roll();
    assertTrue(roll <= 6 && roll >= 1);
  }
}
