package com.example.cricketCards.services;

import com.example.cricketCards.models.CricketPlayer;

public interface CricketService {
	public int comparePlayers(int player1, int player2, String option);
	public CricketPlayer getPlayerWithId(int id);
}
