package com.fu.swp391.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.common.enumConstants.BrokerHeader;
import com.fu.swp391.controller.Transfer.Message;
import com.fu.swp391.controller.Transfer.OutputMessage;
import com.fu.swp391.service.NotificationDispatcher;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotificationsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private static final Logger log = LoggerFactory.getLogger(NotificationsController.class);

    @MessageMapping(BrokerHeader.SOCKJS_ENDPOINT)
    @SendTo(BrokerHeader.NOTIFICATION)
    public OutputMessage sendAll(Message msg) throws Exception {
        OutputMessage out = new OutputMessage(msg.getFrom(), msg.getText(), new SimpleDateFormat("HH:mm").format(new Date()));
        return out;
    }


    @GetMapping("/notification/greetings")
    public String greet(String message){
        System.out.println(message);
        System.out.println("entry");
          return "/notification/noti";
    }

}