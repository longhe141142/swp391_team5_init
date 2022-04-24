package com.fu.swp391.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.service.NotificationDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotificationsController {
    private final NotificationDispatcher dispatcher;
    @Autowired
    public NotificationsController(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    @MessageMapping("/start")
    public void start(StompHeaderAccessor stompHeaderAccessor) {
        dispatcher.add(stompHeaderAccessor.getSessionId());
    }
    @MessageMapping("/stop")
    public void stop(StompHeaderAccessor stompHeaderAccessor) {
        dispatcher.remove(stompHeaderAccessor.getSessionId());
    }

    @MessageMapping("/hello")
    @SendTo("/notification/greetings")
    public ResponseEntity<Object> greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node =mapper.createObjectNode();
        node.put("message",message+ HtmlUtils.htmlEscape(message));
        return new ResponseEntity<Object>(node, HttpStatus.OK);
    }

    @GetMapping("/notification/greetings")
    public String greet(String message){
        System.out.println(message);
        System.out.println("entry");
          return "/notification/noti";
    }

}