package com.example.websocketdemo.controller;

import com.example.websocketdemo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

//the message handling methods
//these methods will be responsible for receiving messages from one client and then broadcasting it to others
@Controller
public class ChatController {

    //public ArrayList<String> onlineUsers = new ArrayList<String>();

    //all the messages sent from clients with a destination starting with /app will be routed to these message
    // handling methods annotated with @MessageMapping

    //a message with destination /app/chat.sendMessage will be routed to the sendMessage()
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    //a message with destination /app/chat.addUser will be routed to the addUser()
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    /*
    @MessageMapping("/chat.addConnectedUser")
    public void addConnectedUser(@Payload ChatMessage chatMessage) {
        onlineUsers.add(chatMessage.getSender());
        System.out.println("CONTROLLER addConnectedUser");

        for (String i : onlineUsers) {
            System.out.println(i);
        }
    }
*/


}
