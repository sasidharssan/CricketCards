package com.example.cricketCards.models;

public class GameInfo {
	private String roomId;
	private int userId;
	private int currentCard;
	private String option;
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCurrentCard() {
		return currentCard;
	}
	public void setCurrentCard(int currentCard) {
		this.currentCard = currentCard;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
}
