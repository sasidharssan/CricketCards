package com.example.cricketCards.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.cricketCards.models.Room;
import com.example.cricketCards.models.User;
import com.example.cricketCards.services.RoomService;
import com.example.cricketCards.services.StackService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private StackService stackService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login() {
		return "login.html";
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register() {
		return "register.html";
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(@RequestParam(name="user") String name, @RequestParam(name="username") String id, HttpSession session) {
		int status = roomService.checkLoggedInStatus(name, id);
		System.out.println("status: "+status);
		int uid = 0;
		ModelAndView mv = null;
		if(status == 1)
			return new ModelAndView("redirect:/?userError=alreadylogged");
		else if(status == 2) {
			mv = new ModelAndView("redirect:/cricAttacksGame");
			User currentUser = stackService.getUserByName(id, name);
			uid = currentUser.getUserId();
		}
		else if(status == -1 || status == 0) {
			int userCount = roomService.getUserCount(id);
			String firstUser = null;
			if(userCount == 2) {
				return new ModelAndView("redirect:/?userError=tooManyUsers");
			} else if(userCount == 0) {
				uid = roomService.addUser(name, id);
				roomService.addFirstPlayer(uid, id);
				firstUser = "y";
			} else {
				List<String> userNames = roomService.getUserNames(id);
				if(userNames.contains(name))
					return new ModelAndView("redirect:/?userError=nameExists");
				uid = roomService.addUser(name, id);
				Room room = roomService.findRoom(id);
				int[] users = room.getUsers();
				ArrayList<Integer> cards = new ArrayList<Integer>();
				for(int i=1; i<=16; i++) {
					cards.add(i);
				}
				Collections.shuffle(cards);
				int[] stackA = cards.subList(0, 8).stream().mapToInt(Integer::intValue).toArray();
				int[] stackB = cards.subList(8, 16).stream().mapToInt(Integer::intValue).toArray();
				stackService.addToStack(users[0], stackA);
				stackService.addToStack(users[1], stackB);
				firstUser = "n";
			}
		mv = new ModelAndView("instruction.html");
		mv.addObject("firstUser", firstUser);
		}
		session.setAttribute("username", name);
		session.setAttribute("roomId", id);
		session.setAttribute("userId", uid);
		return mv;
		
	}

	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerRoom(@Valid Room room, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "redirect:/register?userError=invalidCreds";
		} else {
			try {
				roomService.insertRoom(room);
			} catch (DataIntegrityViolationException error) {
				System.out.println(error);
				return "redirect:/register?userError=roomExists";
			}
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/logoutProcess", method = RequestMethod.POST)
	public String logout(@RequestParam(name="username") String name, @RequestParam(name="roomId") String id) {
		System.out.println("Processing Logout... ");
		roomService.changeLoginStatus(name, id, 2);
		return "redirect:/logout";
	}
		
}
