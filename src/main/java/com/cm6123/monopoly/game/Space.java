package com.cm6123.monopoly.game;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;

public class Space {
  private Spaces spaceType;
  private String label;
  private Player owner;
  private Integer rent;
  private Integer price;

  public Space(Spaces spaceType) {
    Faker faker = new Faker(new Locale("en-GB"));
    this.spaceType = spaceType;
    switch (spaceType) {
      case HOME:
        this.label = "Home";
        break;

      case ROAD:
        this.label = "Road";
        break;

      case PROPERTY:
        this.label = faker.address().streetName();
        break;

      case TAX_OFFICE:
        this.label = "Tax Office";
        break;

      case STATION:
        this.label = faker.harryPotter().spell() + " Station";
        break;

      default:
        throw new IllegalStateException("Unreachable");
    }
    Random rand = new Random();
    this.price = rand.nextInt(200, 500);
    this.rent = this.price * (1 + (rand.nextInt(5, 10) / 100));
  }

  public Spaces getType() {
    return this.spaceType;
  }

  public String getLabel() {
    return this.label;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public Player owner() {
    return this.owner;
  }

  public Payment getPayment(Player player, Integer roll1, Integer roll2) {
    switch (this.spaceType) {
      case PROPERTY:
        if (this.owner.equals(player)) {
          return new Payment(0);
        }
        return new Payment(this.rent);

      case TAX_OFFICE:
        Integer percentage;
        if (roll1 == roll2) {
          percentage = roll1;
        } else {
          percentage = roll1 + roll2;
        }
        return new Payment(player.balance() * (1 + percentage / 100));

      case STATION:
        return new Payment((roll1 + roll2) * this.rent);
      default:
        return new Payment(0);
    }
  }
}
