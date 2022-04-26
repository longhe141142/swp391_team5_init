package com.fu.swp391.service;

import com.fu.swp391.binding.entiity.Email;
import javax.mail.MessagingException;

public interface EmailSenderService {
  public void sendHtmlMessage(Email email) throws MessagingException;
}
