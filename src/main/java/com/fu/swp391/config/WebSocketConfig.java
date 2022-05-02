package com.fu.swp391.config;

import com.fu.swp391.common.enumConstants.BrokerHeader;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(BrokerHeader.NOTIFICATION);//subscribe
        registry.enableSimpleBroker(BrokerHeader.NOTIFICATION);
        registry.setApplicationDestinationPrefixes("/swp391");
        registry.setUserDestinationPrefix("/secured/user");//send message mapping
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(BrokerHeader.SOCKJS_ENDPOINT)
                .setAllowedOrigins("http://127.0.0.1:5500")
                .withSockJS();
    }


}
