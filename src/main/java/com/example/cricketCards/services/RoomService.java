package com.example.cricketCards.services;

import java.util.List;

import com.example.cricketCards.models.Room;

public interface RoomService {
	public void insertRoom(Room room);
	public Room findRoom(String roomId);
	public int addUser(String username, String roomId);
	public int checkLoggedInStatus(String username, String roomId);
	public void changeLoginStatus(String username, String roomId, int status);
	public void addFirstPlayer(int uid, String roomId);
	public int getUserCount(String roomId);
	public List<String> getUserNames(String roomId);
	public int findOtherUser(String roomId, int userId);
}	
