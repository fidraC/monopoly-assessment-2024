package com.cm6123.monopoly.game;

import java.util.Random;

public class Board {
  private Space[] spaces;

  public Board(Integer size) {
    this.spaces = new Space[size];
    this.spaces[0] = new Space(Spaces.HOME);
    Random rand = new Random();
    for (Integer i = 1; i < size; i++) {
      // Get random space type
      this.spaces[i] = new Space(Spaces.values()[rand.nextInt(Spaces.values().length)]);
    }
  }

  public Space space(Integer index) {
    return this.spaces[index];
  }

  public Space[] spaces() {
    return this.spaces;
  }
}

enum Target {
  BANKER,
  PLAYER
}

class Payment {
  private Integer amount;
  private Target target;

  public Payment(Integer amount) {
    this.amount = amount;
    this.target = Target.BANKER;
  }

  public Payment(Integer amount, Target target) {
    this.target = target;
    this.amount = amount;
  }

  public Integer amount() {
    return this.amount;
  }

  public Target target() {
    return this.target;
  }
}
