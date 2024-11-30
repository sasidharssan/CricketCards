package com.example.cricketCards.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.cricketCards.models.GameInfo;
import com.example.cricketCards.models.GameInfoRowMapper;
import com.example.cricketCards.models.User;
import com.example.cricketCards.models.UserRowMapper;

@Component
public class StackInfoDaoImpl implements StackInfoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserRowMapper userRowMapper;
	
	@Autowired
	private GameInfoRowMapper gameInfoRowMapper;

	@Override
	public void addToStack(int uid, int[] playerIds) {
		String addQuery = "UPDATE user_stack SET stack = array_cat(stack, ?) WHERE user_id=?";
		jdbcTemplate.update(addQuery, playerIds, uid);
	}

	@Override
	public void removeFromStack(int uid) {
		// TODO Auto-generated method stub
		String removeQuery = "UPDATE user_stack SET stack = stack[2:array_length(stack,1)] WHERE user_id=?";
		jdbcTemplate.update(removeQuery, uid);
	}

	@Override
	public void updateGame(String roomId, int playerId, String option) {
		// TODO Auto-generated method stub
		String updateGameInfo = "UPDATE game_info SET current_card=?, option_clicked=? WHERE room_id = ?";
		jdbcTemplate.update(updateGameInfo, playerId, option, roomId);
	}

	@Override
	public void updateGameUser(String roomId, int uid) {
		// TODO Auto-generated method stub
		String updateGameInfo = "UPDATE game_info SET user_id=? WHERE room_id = ?";
		jdbcTemplate.update(updateGameInfo, uid, roomId);
	}

	@Override
	public int getTopCard(int uid) {
		// TODO Auto-generated method stub
		String getTopCard = "SELECT STACK[1] FROM USER_STACK WHERE user_id=?";
		int topCard = jdbcTemplate.queryForObject(getTopCard, Integer.class, uid);
		return topCard;
	}

	@Override
	public int getCardCount(int uid) {
		// TODO Auto-generated method stub
		String countQuery = "SELECT ARRAY_LENGTH(STACK, 1) FROM USER_STACK WHERE user_id=?";
		int count = 0;
		Integer countValue = jdbcTemplate.queryForObject(countQuery, Integer.class, uid);
		if(countValue != null) {
			count = countValue.intValue();
		}
		return count;
	}

	@Override
	public User getUserById(int uid) {
		// TODO Auto-generated method stub
		String selectQuery = "SELECT user_id, username, room_id, stack, logged_in FROM USER_STACK WHERE user_id=?";
		return jdbcTemplate.queryForObject(selectQuery, userRowMapper, uid);
	}

	@Override
	public User getUserByName(String roomId, String username) {
		// TODO Auto-generated method stub
		String selectQuery = "SELECT user_id, username, room_id, stack, logged_in FROM USER_STACK WHERE USERNAME = ? AND ROOM_ID = ?";
		return jdbcTemplate.queryForObject(selectQuery, userRowMapper, username, roomId);
	}
	
	@Override
	public GameInfo getFirstPlayer(String roomId) {
		String selectQuery = "SELECT * FROM game_info WHERE ROOM_ID = ?";
		return jdbcTemplate.queryForObject(selectQuery, gameInfoRowMapper, roomId);
	}

}
