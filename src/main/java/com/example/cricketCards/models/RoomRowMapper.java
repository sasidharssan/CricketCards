package com.example.cricketCards.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.core.util.ArrayUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.cricketCards.models.Room;

@Component
public class RoomRowMapper implements RowMapper<Room> {

	@Override
	public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
		Room room = new Room();
		room.setRoomId(rs.getString("room_id"));
		room.setPassword(rs.getString("password"));
		Array users = rs.getArray("users");
		if(users != null) {
			Integer[] integerIds = (Integer[]) users.getArray();
			int length = integerIds.length;
			int[] userIds = new int[length];
			for(int i=0; i < length; i++) {
				userIds[i] = integerIds[i].intValue();
			}
			room.setUsers(userIds);
		}
		else
			room.setUsers(null);
		return room;
	}
}

