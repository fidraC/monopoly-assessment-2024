package com.cm6123.monopoly.game;

public class Player {
	private String name;
	private Integer location;
	private final Integer boardSize;
	private Integer balance;

	public Player(String name, Integer boardSize) {
		this.name = name;
		this.boardSize = boardSize;
	}

	public void setLocation(Integer roll) {
		this.location = (this.location + roll) % boardSize;
	}

	public Integer location() {
		return this.location;
	}

	public Integer balance() {
		return this.balance;
	}

	public void withdraw(Integer amount) {
		if (this.balance < amount) {
			throw new IllegalStateException("Out of money");
		}
		this.balance = this.balance - amount;
	}

	public void deposit(Integer amount) {
		this.balance = this.balance + amount;
	} 

	public String String() {
		return this.name;
	}
}
