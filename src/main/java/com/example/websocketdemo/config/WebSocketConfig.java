package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
//to enable WebSocket server
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //register a websocket endpoint that the clients will use to connect to websocket server
    //STOMP = Simple Text Oriented Messaging Protocol - protocol that defines the format and rules for data exchange
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //SockJS is used to enable fallback options for browsers that don’t support websocket
        //Registers the endpoint where the handshake will take place
        registry.addEndpoint("/ws").withSockJS();
    }

    //configuring a message broker that is use to route messages from one client to another
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //Set prefix for endpoints the client will send messages to
        //the messages whose destination starts with “/app” should be routed to message-handling methods
        registry.setApplicationDestinationPrefixes("/app");

        //the messages whose destination starts with “/topic” should be routed to the message broker
        //message broker broadcasts messages to all the connected clients who are subscribed to a particular topic
        registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker



    }
}
