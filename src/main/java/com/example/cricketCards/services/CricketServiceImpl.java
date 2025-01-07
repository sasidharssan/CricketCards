package com.example.cricketCards.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cricketCards.dao.CricketDao;
import com.example.cricketCards.models.CricketPlayer;

import jakarta.transaction.Transactional;

@Component
public class CricketServiceImpl implements CricketService {
	
	@Autowired
	private CricketDao cricketDao;

	@Override
	@Transactional
	public int comparePlayers(int playerId1, int playerId2, String option) {
		// TODO Auto-generated method stub
		CricketPlayer playerA = cricketDao.findById(playerId1).orElse(null);
		CricketPlayer playerB = cricketDao.findById(playerId2).orElse(null);
		switch(option) {
			case "matches":
				if(playerA.getMatches() <= playerB.getMatches())
					return 0;
				break;
			case "runs":
				if(playerA.getRuns() <= playerB.getRuns())
					return 0;
				break;
			case "ballsFaced":
				if(playerA.getBallsFaced() <= playerB.getBallsFaced())
					return 0;
				break;
			case "highest":
				if(playerA.getHighest() <= playerB.getHighest())
					return 0;
				break;
			case "strikeRate":
				if(playerA.getStrikeRate() <= playerB.getStrikeRate())
					return 0;
				break;
			case "batAverage":
				if(playerA.getBatAverage() <= playerB.getBatAverage())
					return 0;
				break;
			case "centuries":
				if(playerA.getCenturies() <= playerB.getCenturies())
					return 0;
				break;
			case "wickets":
				if(playerA.getWickets() <= playerB.getWickets())
					return 0;
				break;
			case "best":
				int[] bestA = bestFigures(playerA);
				int[] bestB = bestFigures(playerB);
				if(bestA[0] > bestB[0]) 
					return 1;
				else if(bestA[0] == bestB[0]) {
					if(bestA[1] < bestB[1])
						return 1;
					else
						return 0;
				} else {
					return 0;
				} 
			case "fifers":
				if(playerA.getFifers() <= playerB.getFifers())
					return 0;
				break;
			case "ballsBowled":
				if(playerA.getBallsBowled() <= playerB.getBallsBowled())
					return 0;
				break;
			case "economy":
				if(playerA.getEconomy() >= playerB.getEconomy())
					return 0;
				break;
			case "bowlingAverage":
				if(playerA.getBowlAverage() >= playerB.getBowlAverage())
					return 0;
				break;
		}
		return 1;
	}
	
	public int[] bestFigures(CricketPlayer playerA) {
		int[] bestFigs = new int[2];
		String best = playerA.getBest();
		String[] parts = best.split("/");
		
		bestFigs[0] = Integer.parseInt(parts[0]);
		bestFigs[1] = Integer.parseInt(parts[1]);
		return bestFigs;
	}

	@Override
	public CricketPlayer getPlayerWithId(int id) {
		// TODO Auto-generated method stub
		return cricketDao.findById(id).orElse(null);
	}
}
