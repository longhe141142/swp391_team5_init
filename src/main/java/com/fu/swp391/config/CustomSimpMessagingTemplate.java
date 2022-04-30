package com.fu.swp391.config;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class CustomSimpMessagingTemplate extends SimpMessagingTemplate{

  /**
   * Create a new {@link SimpMessagingTemplate} instance.
   *
   * @param messageChannel the message channel (never {@code null})
   */
  public CustomSimpMessagingTemplate(MessageChannel messageChannel) {
    super(messageChannel);
  }

  public void convertAndNotifyUser(String user, String destination, Object payload) throws MessagingException {
    convertAndSendToUser(user, destination, payload, (MessagePostProcessor) null);
  }

}
