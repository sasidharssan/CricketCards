package com.example.cricketCards.dao;

import com.example.cricketCards.models.GameInfo;
import com.example.cricketCards.models.User;

public interface StackInfoDao {
	public void addToStack(int uid, int[] playerId);
	public void removeFromStack(int uid);
	public void updateGame(String roomId, int playerId, String option);
	public void updateGameUser(String roomId, int uid);
	public int getTopCard(int uid);
	public int getCardCount(int uid);
	public User getUserById(int uid);
	public User getUserByName(String roomId, String username);
	public GameInfo getFirstPlayer(String roomId);
}
