package com.cm6123.monopoly.game;

import com.cm6123.monopoly.dice.Dice;

public class Monopoly {
	private Board board;
	private Player[] players;
	private int currentPlayerIndex;
	private boolean hasActed;

	private static final Integer MIN_PLAYER_COUNT = 2;
	private static final Integer MAX_PLAYER_COUNT = 10;
	private static final Integer MIN_BOARD_SIZE = 10;
	private static final Integer MAX_BOARD_SIZE = 50;
	private static final Dice dice = new Dice(6);

	public Monopoly(Integer playerCount, Integer boardSize) {
		if (playerCount < MIN_PLAYER_COUNT || playerCount > MAX_PLAYER_COUNT) {
			throw new IllegalArgumentException(
					"Player count must be between " + MIN_PLAYER_COUNT + " and " + MAX_PLAYER_COUNT);
		}
		if (boardSize < MIN_BOARD_SIZE || boardSize > MAX_BOARD_SIZE) {
			throw new IllegalArgumentException(
					"Board size must be between " + MIN_BOARD_SIZE + " and " + MAX_BOARD_SIZE);
		}
		this.board = new Board(boardSize);
		this.players = new Player[playerCount];
		for (int i = 0; i < playerCount; i++) {
			this.players[i] = new Player("Player " + (i + 1), boardSize);
		}
		this.currentPlayerIndex = 0;
	}

	public void bankrupt(Player player) {
		// Not implemented
	}

	public void roll() {
		this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.length;
		this.hasActed = false;
		Integer roll1 = dice.roll();
		Integer roll2 = dice.roll();

		this.players[this.currentPlayerIndex].setLocation(roll1 + roll2);

		// Payment
		Space space = this.board.space(this.players[this.currentPlayerIndex].location());
		Payment payment = space.getPayment(this.players[this.currentPlayerIndex], roll1, roll2);
		try {
			this.players[this.currentPlayerIndex].withdraw(payment.amount());
		} catch (IllegalStateException e) {
			this.bankrupt(this.players[this.currentPlayerIndex]);
		}
		if (payment.target() == Target.PLAYER) {
			space.owner().deposit(payment.amount());
		}

	}

	public void buy() {
		if (this.hasActed) {
			throw new IllegalCallerException("You already did something");
		}
		this.hasActed = true;
	}
}
