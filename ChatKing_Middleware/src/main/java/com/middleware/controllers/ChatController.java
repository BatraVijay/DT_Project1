package com.middleware.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.backend.model.Chat;
import com.backend.model.User;

@Controller
public class ChatController {

	/*SimpMessagingTemplate provides methods for sending messages to a 
	 user*/
	private SimpMessagingTemplate messagingTemplate=null;

	private List<String> users=new ArrayList<String>();
	
	@Autowired
	public ChatController(SimpMessagingTemplate messagingTemplate) {
		super();
		this.messagingTemplate = messagingTemplate;
	}
	
	
	@SubscribeMapping("/join/{username}")
	public List<String> join(@DestinationVariable String username){
		
		System.out.println("Newly joined username is "+username);
		if(!users.contains(username))
			users.add(username);
		messagingTemplate.convertAndSend("/topic/join", username);
		return users;
	}
	
	@MessageMapping(value="/chat")
	public void chatRecieved(Chat chat)
	{
		if(chat.getTo().equals("all"))//group chat
		{
			messagingTemplate.convertAndSend("/queue/chats", chat);
		}
		else{//private chat
			
			messagingTemplate.convertAndSend("/queue/chats/"+chat.getTo(), chat);
			messagingTemplate.convertAndSend("/queue/chats/"+chat.getFrom(), chat);
		
			
		}
	}

	
}
