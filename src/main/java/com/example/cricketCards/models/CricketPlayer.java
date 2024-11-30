package com.example.cricketCards.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CRIC_STATS")
public class CricketPlayer {
	
	@Id
	@Column(name="PLAYER_ID")
	private int playerId;
	
	@Column(name="PLAYER_NAME")
	private String name;
	
	@Column(name="PLAYER_DESIGNATION")
	private String designation;
	
	@Column(name="MATCHES")
	private int matches;
	
	@Column(name="RUNS")
	private int runs;
	
	@Column(name="BALLS_FACED")
	private int ballsFaced;
	
	@Column(name="highest_score")
	private int highest;
	
	@Column(name="strike_rate")
	private float strikeRate;
	
	@Column(name="AVERAGE")
	private float batAverage;

	@Column(name="centuries")
	private int centuries;

	@Column(name="wickets")
	private int wickets;
	
	@Column(name="bowling_best")
	private String best;
	
	@Column(name="fifers")
	private int fifers;
	
	@Column(name="balls_bowled")
	private int ballsBowled;
	
	@Column(name="economy")
	private float economy;
	
	@Column(name="bowling_avg")
	private float bowlAverage;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getBallsFaced() {
		return ballsFaced;
	}

	public void setBallsFaced(int ballsFaced) {
		this.ballsFaced = ballsFaced;
	}

	public int getHighest() {
		return highest;
	}

	public void setHighest(int highest) {
		this.highest = highest;
	}

	public float getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(float strikeRate) {
		this.strikeRate = strikeRate;
	}

	public float getBatAverage() {
		return batAverage;
	}

	public void setBatAverage(float batAverage) {
		this.batAverage = batAverage;
	}

	public int getCenturies() {
		return centuries;
	}

	public void setCenturies(int centuries) {
		this.centuries = centuries;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public String getBest() {
		return best;
	}

	public void setBest(String best) {
		this.best = best;
	}

	public int getFifers() {
		return fifers;
	}

	public void setFifers(int fifers) {
		this.fifers = fifers;
	}

	public int getBallsBowled() {
		return ballsBowled;
	}

	public void setBallsBowled(int ballsBowled) {
		this.ballsBowled = ballsBowled;
	}

	public float getEconomy() {
		return economy;
	}

	public void setEconomy(float economy) {
		this.economy = economy;
	}

	public float getBowlAverage() {
		return bowlAverage;
	}

	public void setBowlAverage(float bowlAverage) {
		this.bowlAverage = bowlAverage;
	}
}
