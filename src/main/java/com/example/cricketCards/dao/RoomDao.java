package com.example.cricketCards.dao;

import java.util.List;

import com.example.cricketCards.models.Room;

public interface RoomDao {
	public void insertRoom(Room room);
	public Room findRoom(String roomId);
	public int addUser(String username, String roomId);
	public int checkLoggedIn(String username, String roomId);
	public void changeLoginStatus(String username, String roomId, int status);
	public void addFirstPlayer(int uid, String roomId);
	public int getUserCount(String roomId);
	public List<String> getUserNames(String roomId);
	public void removeUser(String roomId, int uid);
}
