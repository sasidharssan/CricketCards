package com.example.cricketCards.services;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	Logger logger = LogManager.getLogger(StackService.class);

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void addToStack(int uid, int[] playerIds) {
		// TODO Auto-generated method stub
		logger.info("Adding new card to: " + uid);
		stackDao.addToStack(uid, playerIds);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void removeFromStack(int uid) {
		// TODO Auto-generated method stub
		logger.info("Removing new card from: " + uid);
		stackDao.removeFromStack(uid);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void updateGame(String roomId, int playerId, String option) {
		// TODO Auto-generated method stub
		logger.info("Next card: " + playerId);
		stackDao.updateGame(roomId, playerId, option);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void updateGameUser(String roomId, int uid) {
		// TODO Auto-generated method stub
		logger.info("User control moves to: " + uid);
		stackDao.updateGameUser(roomId, uid);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public int getTopCard(int uid) {
		// TODO Auto-generated method stub
		logger.info("getting card from: " + uid);
		return stackDao.getTopCard(uid);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public int getCardCount(int uid) {
		// TODO Auto-generated method stub
		int count = stackDao.getCardCount(uid);
		logger.info("Card Count for {}: {}", count, uid);
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
