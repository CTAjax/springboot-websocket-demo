package com.ajax.websocketdemo.controller;

import com.ajax.websocketdemo.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import static com.ajax.websocketdemo.Rooms.PUBLIC_ROOM;

/**
 * @author ajax
 */
@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendTo(PUBLIC_ROOM)
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo(PUBLIC_ROOM)
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
