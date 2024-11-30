package com.example.cricketCards.models;

public class User {
	private int userId;
	private String username;
	private String room;
	private int[] cardStack;
	private int loggedIn;
	public int getUserId() {
		return userId;
	}
	public User(String userId, String username, String room, int[] cardStack, int loggedIn) {
		super();
		this.userId = Integer.parseInt(userId);
		this.username = username;
		this.room = room;
		this.cardStack = cardStack;
		this.loggedIn = loggedIn;
	}
	public User() {
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int[] getCardStack() {
		return cardStack;
	}
	public void setCardStack(int[] cardStack) {
		this.cardStack = cardStack;
	}
	public int getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(int loggedIn) {
		this.loggedIn = loggedIn;
	}
}
