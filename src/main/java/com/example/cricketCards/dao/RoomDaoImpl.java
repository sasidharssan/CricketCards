package com.example.cricketCards.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.cricketCards.models.Room;
import com.example.cricketCards.models.RoomRowMapper;

@Component
public class RoomDaoImpl implements RoomDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RoomRowMapper roomRowMapper;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public void insertRoom(Room room) {
		String insertQuery = "INSERT INTO ROOMS (ROOM_ID, PASSWORD, DATE) VALUES (?, ?, CURRENT_DATE)";
		jdbcTemplate.update(insertQuery, room.getRoomId(), encoder.encode(room.getPassword()));
	}

	public Room findRoom(String roomId) {
		String selectQuery = "SELECT ROOM_ID, PASSWORD, USERS FROM ROOMS WHERE ROOM_ID = ?";
		return jdbcTemplate.queryForObject(selectQuery, roomRowMapper, roomId);
	}

	public int addUser(String username, String roomId) {
		String addToStack = "INSERT INTO USER_STACK (ROOM_ID, USERNAME) VALUES (?, ?)";
		jdbcTemplate.update(addToStack, roomId, username);
	
		String getUserId = "SELECT USER_ID FROM USER_STACK WHERE USERNAME = ? AND ROOM_ID = ?";
		int uid = jdbcTemplate.queryForObject(getUserId, Integer.class, username, roomId);
		
		String addQuery = "UPDATE ROOMS SET USERS = ARRAY_APPEND(USERS, ?) WHERE ROOM_ID = ?";
		jdbcTemplate.update(addQuery, uid, roomId);
		
		return uid;
	}

	public int checkLoggedIn(String username, String roomId) {
		String getLoginStatus = "SELECT LOGGED_IN FROM USER_STACK WHERE USERNAME = ? AND ROOM_ID = ?";
		return jdbcTemplate.queryForObject(getLoginStatus, Integer.class, username, roomId);
	}

	public void changeLoginStatus(String username, String roomId, int status) {
		String updateLoginStatus = "UPDATE USER_STACK SET LOGGED_IN = ? WHERE USERNAME = ? AND ROOM_ID = ?";
		jdbcTemplate.update(updateLoginStatus, status, username, roomId);
	}

	@Override
	public void addFirstPlayer(int uid, String roomId) {
		// TODO Auto-generated method stub
		String addfp = "INSERT INTO GAME_INFO (ROOM_ID, USER_ID) VALUES (?, ?)";
		jdbcTemplate.update(addfp, roomId, uid);
	}
	
	public int getUserCount(String roomId) {
		String getLoginStatus = "SELECT COUNT(username) FROM USER_STACK WHERE ROOM_ID = ?";
		return jdbcTemplate.queryForObject(getLoginStatus, Integer.class, roomId);
	}

	@Override
	public List<String> getUserNames(String roomId) {
		// TODO Auto-generated method stub
		String selectNames = "SELECT USERNAME FROM USER_STACK WHERE ROOM_ID=?";
		return jdbcTemplate.queryForList(selectNames, String.class, roomId);
	}

	@Override
	public void removeUser(String roomId, int uid) {
		// TODO Auto-generated method stub
		if(getUserCount(roomId) == 1) {
			String removeQuery = "DELETE from rooms WHERE room_id=?";
			jdbcTemplate.update(removeQuery, roomId);
		} else {
			String removeQuery = "DELETE from user_stats WHERE user_id=?";
			jdbcTemplate.update(removeQuery, roomId);
		}
	}
}


