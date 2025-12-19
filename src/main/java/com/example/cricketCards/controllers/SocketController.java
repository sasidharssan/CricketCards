package com.example.cricketCards.controllers;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	Logger logger = LogManager.getLogger(SocketController.class);
	
	@EventListener
	public void handleSessionConnectEvent(SessionConnectEvent event) {
		logger.debug("Session Connect Event");
	}
	
	@EventListener
	public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
		logger.debug("Session Disconnect Event");
	}
	
	@MessageMapping("/signal")
	public void sendSignal(Message message) {
		String roomId = message.user().getRoom();
		int userId = message.user().getUserId();
		int receiverId = 0;
		Message newMessage = new Message(new User("0", message.user().getUsername(), 
				roomId, null, message.user().getLoggedIn()), 
				message.signal());
		if(message.signal() == "DISCONNECTED") {
			roomService.changeLoginStatus(message.user().getUsername(), 
					message.user().getRoom(), 2);
		}
		receiverId = roomService.findOtherUser(roomId, userId);
		logger.debug("signal sent from {}", userId);
		simpMessagingTemplate.convertAndSendToUser(String.valueOf(receiverId), "/topic/signal", newMessage);
	}
	
	@MessageMapping("/chat")
	public void sendChatMessage(Message message) {
		String roomId = message.user().getRoom();
		int userId = message.user().getUserId();
		int receiverId = 0;
		Message newMessage = new Message(new User("0", message.user().getUsername(), 
				roomId, null, message.user().getLoggedIn()), 
				message.signal());
		receiverId = roomService.findOtherUser(roomId, userId);
		logger.debug("message sent from {} to {}", userId, receiverId);
		simpMessagingTemplate.convertAndSendToUser(String.valueOf(receiverId), "/topic/chat", newMessage);
	}
	
	
}
