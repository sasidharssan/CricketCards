package com.example.cricketCards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cricketCards.models.CricketPlayer;
import com.example.cricketCards.models.GameInfo;
import com.example.cricketCards.models.User;
import com.example.cricketCards.services.CricketService;
import com.example.cricketCards.services.RoomService;
import com.example.cricketCards.services.StackService;

import jakarta.servlet.http.HttpSession;

import com.example.cricketCards.models.MatchResult;

@Controller
public class GameController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private StackService stackService;
	
	@Autowired
	private CricketService cricketService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homepage(@RequestParam(name="roomId") String id, 
			@RequestParam(name="userId") String uid, Model model,
			HttpSession session) {
		int userId = Integer.parseInt(uid);
		List<String> userNames = roomService.getUserNames(id);
		User user = stackService.getUserById(userId);
		String uname = user.getUsername();
		roomService.changeLoginStatus(uname, id, 1);
		int topCard = stackService.getTopCard(userId);
		CricketPlayer player1 = cricketService.getPlayerWithId(topCard);
		int firstPlayer = stackService.getFirstPlayer(id).getUserId();
		int cardCount = stackService.getCardCount(userId);
		
		model.addAttribute("userId", uid);
		model.addAttribute("roomId", id);
		model.addAttribute("username", uname);
		model.addAttribute("allUsers", userNames);
		model.addAttribute("player1", player1);
		model.addAttribute("firstPlayer", firstPlayer);
		model.addAttribute("count", cardCount);	
		return "home.html";
	}
	
	@PostMapping(value = "/submit")
	public ResponseEntity<MatchResult> submitCard(@RequestParam String roomId, 
			@RequestParam String uid, 
			@RequestParam String option) {
		int userId = Integer.parseInt(uid);
		MatchResult result = new MatchResult();
		int otherUser = roomService.findOtherUser(roomId, userId);
		int myTopCardId = stackService.getTopCard(userId);
		int oppTopCardId = stackService.getTopCard(otherUser);
		stackService.updateGame(roomId, myTopCardId, option);
		
		int outcome = cricketService.comparePlayers(myTopCardId, oppTopCardId, option);
		
		CricketPlayer oppPlayer = cricketService.getPlayerWithId(oppTopCardId);
		int cardCount = stackService.getCardCount(userId);
		if(outcome == 0) {
			cardCount = cardCount - 1;
			result.setWinnerUser(otherUser);
		}
		else {
			cardCount = cardCount + 1;
			result.setWinnerUser(userId);
		}
		
		result.setOppPlayer(oppPlayer);
		result.setCardCount(cardCount);
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping(value = "/result")
	public ResponseEntity<MatchResult> getResult(@RequestParam String roomId, 
			@RequestParam String uid) {
		int userId = Integer.parseInt(uid);
		MatchResult result = new MatchResult();
		int myTopCardId = stackService.getTopCard(userId);
		GameInfo game = stackService.getFirstPlayer(roomId);
		
		int oppTopCardId = game.getCurrentCard();
		int otherUser = game.getUserId();
		String option = game.getOption();
		
		int outcome = cricketService.comparePlayers(myTopCardId, oppTopCardId, option);
		
		CricketPlayer oppPlayer = cricketService.getPlayerWithId(oppTopCardId);
		
		stackService.removeFromStack(userId);
		stackService.removeFromStack(otherUser);
		
		if(outcome == 0) {
			int[] cards = {myTopCardId, oppTopCardId};
			stackService.addToStack(otherUser, cards);
			result.setWinnerUser(otherUser);
		}
		else {
			int[] cards = {myTopCardId, oppTopCardId};
			stackService.addToStack(userId, cards);
			stackService.updateGameUser(roomId, userId);
			result.setWinnerUser(userId);
		}
		
		int cardCount = stackService.getCardCount(userId);
		
		result.setOppPlayer(oppPlayer);
		result.setCardCount(cardCount);
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping(value = "/drawCard")
	public ResponseEntity<CricketPlayer> drawCard(@RequestParam String roomId, 
			@RequestParam String uid) {
		int userId = Integer.parseInt(uid);
		int topCardId = stackService.getTopCard(userId);
		CricketPlayer topCard = cricketService.getPlayerWithId(topCardId);
		return ResponseEntity.ok(topCard);
	}
}
