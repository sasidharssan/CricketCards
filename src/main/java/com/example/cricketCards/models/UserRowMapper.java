package com.example.cricketCards.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setUsername(rs.getString("username"));
		user.setRoom(rs.getString("room_id"));
//		Array stack = rs.getArray("stack");
//		Integer[] integerStack = (Integer[]) stack.getArray();
//		if(stack != null) 
//			user.setCardStack(integerStack);
//		else
//			user.setCardStack(null);
		user.setLoggedIn(rs.getInt("logged_in"));
		return user;
	}

}
