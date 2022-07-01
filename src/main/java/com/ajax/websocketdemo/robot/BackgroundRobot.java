package com.ajax.websocketdemo.robot;

import com.ajax.websocketdemo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

import static com.ajax.websocketdemo.Rooms.PUBLIC_ROOM;

/**
 * @author ajax
 */
@Component
@Configuration
@EnableScheduling
public class BackgroundRobot {
    @Autowired
    SimpMessagingTemplate template;

    @Scheduled(cron = "*/30 * * * * ?")
    public void sendNoisy() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("background message... " + ZonedDateTime.now());
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        chatMessage.setSender("robot");
        template.convertAndSend(PUBLIC_ROOM, chatMessage);
    }
}
