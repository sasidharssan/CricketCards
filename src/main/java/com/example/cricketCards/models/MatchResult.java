package com.example.cricketCards.models;

public class MatchResult {
	private CricketPlayer oppPlayer;
	private int winnerUser;
	private int cardCount;
	private String option;
	private String finalResult;

	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
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
	public String getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
}
