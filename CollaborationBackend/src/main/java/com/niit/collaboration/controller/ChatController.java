package com.niit.collaboration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.collaboration.model.Message;
import com.niit.collaboration.model.OutputMessage;

@Controller
public class ChatController {
	
	private static final Logger log=LoggerFactory.getLogger(ChatController.class);
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	
	public OutputMessage sendMessage(Message message){
		log.debug("calling of the method sendmessage");
		log.debug("Message:",message.getMessage());
		log.debug("Message ID:",message.getId());
		
		return null;
		
		
	}
	
	
	

}
