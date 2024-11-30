package com.example.cricketCards.models;

public class MatchResult {
	private CricketPlayer oppPlayer;
	private int winnerUser;
	private int cardCount;

	public int getWinnerUser() {
		return winnerUser;
	}
	public void setWinnerUser(int winnerUser) {
		this.winnerUser = winnerUser;
	}
	public CricketPlayer getOppPlayer() {
		return oppPlayer;
	}
	public void setOppPlayer(CricketPlayer oppPlayer) {
		this.oppPlayer = oppPlayer;
	}
	public int getCardCount() {
		return cardCount;
	}
	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}
}
