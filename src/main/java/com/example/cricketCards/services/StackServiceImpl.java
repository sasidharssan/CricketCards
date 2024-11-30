package com.example.cricketCards.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cricketCards.dao.StackInfoDao;
import com.example.cricketCards.models.GameInfo;
import com.example.cricketCards.models.User;

import jakarta.transaction.Transactional;

@Component
public class StackServiceImpl implements StackService {
	
	@Autowired
	private StackInfoDao stackDao;

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void addToStack(int uid, int[] playerIds) {
		// TODO Auto-generated method stub
		System.out.println("Adding new card to: " + uid);
		stackDao.addToStack(uid, playerIds);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void removeFromStack(int uid) {
		// TODO Auto-generated method stub
		System.out.println("Removing new card to: " + uid);
		stackDao.removeFromStack(uid);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void updateGame(String roomId, int playerId, String option) {
		// TODO Auto-generated method stub
		System.out.println("Next card: " + playerId);
		stackDao.updateGame(roomId, playerId, option);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void updateGameUser(String roomId, int uid) {
		// TODO Auto-generated method stub
		System.out.println("User control moves to: " + uid);
		stackDao.updateGameUser(roomId, uid);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public int getTopCard(int uid) {
		// TODO Auto-generated method stub
		System.out.println("getting card from: " + uid);
		return stackDao.getTopCard(uid);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public int getCardCount(int uid) {
		// TODO Auto-generated method stub
		int count = stackDao.getCardCount(uid);
		System.out.println("Count: " + count);
		return count;
	}

	@Override
	public User getUserById(int uid) {
		// TODO Auto-generated method stub
		return stackDao.getUserById(uid);
	}

	@Override
	public User getUserByName(String roomId, String username) {
		// TODO Auto-generated method stub
		return stackDao.getUserByName(roomId, username);
	}

	@Override
	public GameInfo getFirstPlayer(String roomId) {
		// TODO Auto-generated method stub
		return stackDao.getFirstPlayer(roomId);
	}

}
