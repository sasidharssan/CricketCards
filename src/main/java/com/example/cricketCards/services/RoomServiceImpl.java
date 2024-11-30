package com.example.cricketCards.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.example.cricketCards.dao.RoomDao;
import com.example.cricketCards.models.Room;

import jakarta.transaction.Transactional;

@Component
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;
	
	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void insertRoom(Room room) {
		roomDao.insertRoom(room);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public Room findRoom(String roomId) {
		return roomDao.findRoom(roomId);
	}
	
	@Override
	@Transactional(rollbackOn = SQLException.class)
	public int addUser(String username, String roomId) {
		return roomDao.addUser(username, roomId);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public int checkLoggedInStatus(String username, String roomId) {
		int val = 0;
		try {
			val = roomDao.checkLoggedIn(username, roomId);
		} catch (EmptyResultDataAccessException e) {
			val = -1;
		}

		return val;
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void changeLoginStatus(String username, String roomId, int status) {
		// TODO Auto-generated method stub
		roomDao.changeLoginStatus(username, roomId, status);
	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public void addFirstPlayer(int uid, String roomId) {
		// TODO Auto-generated method stub
		roomDao.addFirstPlayer(uid, roomId);
	}

	@Override
	public int getUserCount(String roomId) {
		// TODO Auto-generated method stub
		int val = roomDao.getUserCount(roomId);
		return val;
	}

	@Override
	public List<String> getUserNames(String roomId) {
		// TODO Auto-generated method stub
		return roomDao.getUserNames(roomId);
	}
	
	@Override
	public int findOtherUser(String roomId, int userId) {
		Room room = roomDao.findRoom(roomId);
		int[] users = room.getUsers();
		int receiverId = 0;
		if(users[0]==userId) {
			receiverId = users[1];
		} else {
			receiverId = users[0];
		}
		return receiverId;
	}

}	
