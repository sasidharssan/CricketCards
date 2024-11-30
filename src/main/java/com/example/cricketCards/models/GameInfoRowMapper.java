package com.example.cricketCards.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GameInfoRowMapper implements RowMapper<GameInfo> {

	@Override
	@Nullable
	public GameInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		GameInfo game = new GameInfo();
		game.setRoomId(rs.getString("room_id"));
		game.setUserId(rs.getInt("user_id"));
		game.setOption(rs.getString("option_clicked"));
		game.setCurrentCard(rs.getInt("current_card"));
		return game;
	}

}
