package com.example.cricketCards.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.cricketCards.models.Message;
import com.example.cricketCards.models.Room;
import com.example.cricketCards.models.User;
import com.example.cricketCards.services.RoomService;
import com.example.cricketCards.services.StackService;

@Controller
public class SocketController {
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private StackService stackService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@EventListener
	public void handleSessionConnectEvent(SessionConnectEvent event) {
		System.out.println("Session Connect Event");
	}
	
	@EventListener
	public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
		System.out.println("Session Disconnect Event");
	}
	
	public void sendMessage(Message message, String url) {
		String roomId = message.user().getRoom();
		int userId = message.user().getUserId();
		int receiverId = 0;
		Message newMessage = new Message(new User("0", message.user().getUsername(), 
				roomId, null, message.user().getLoggedIn()), 
				message.signal());
		receiverId = roomService.findOtherUser(roomId, userId);
		simpMessagingTemplate.convertAndSendToUser(String.valueOf(receiverId), "/topic"+url, newMessage);
	}
	
	@MessageMapping("/joined")
	public void getJoinedMessage(Message message) {
		sendMessage(message, "/joined");
	}
	
	@MessageMapping("/submitted")
	public void getSubmitMessage(Message message) {
		sendMessage(message, "/submitted");
	}
	
	@MessageMapping("/placed")
	public void getPlacedMessage(Message message) {
		sendMessage(message, "/placed");
	}
	
	@MessageMapping("/disconnect")
	public void getDisconnectMessage(Message message) {
		sendMessage(message, "/disconnect");
		roomService.changeLoginStatus(message.user().getUsername(), 
				message.user().getRoom(), 2);
	}
	
}