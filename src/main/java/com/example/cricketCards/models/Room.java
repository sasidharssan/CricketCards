package com.example.cricketCards.models;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Room {
	@NotEmpty(message = "room ID is compulsory")
	private String roomId;
	@NotEmpty(message = "password is mandatory")
	private String password;
	private int[] users;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int[] getUsers() {
		return users;
	}

	public void setUsers(int[] users) {
		this.users = users;
	}

}
