package com.example.cricketCards.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.cricketCards.models.CricketPlayer;

public interface CricketDao extends CrudRepository<CricketPlayer, Integer>{
	
}
