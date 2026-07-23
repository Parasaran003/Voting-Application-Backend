package com.voting.app.demo.config; // Adjust to your actual package

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // This is the URL Angular will use to establish the connection.
        // We explicitly allow localhost:4200 to prevent CORS blocking the socket.
        registry.addEndpoint("/ws")
                .setAllowedOrigins("https://your-frontend-name.vercel.app");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // This is the "topic" prefix where we will broadcast our live updates.
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}