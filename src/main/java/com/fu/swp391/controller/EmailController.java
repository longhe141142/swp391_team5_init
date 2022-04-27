package com.fu.swp391.controller;

import com.fu.swp391.binding.entiity.Email;
import com.fu.swp391.service.EmailSenderService;
import com.fu.swp391.service.EmailSenderServiceImpl;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "sendMail")
public class EmailController {

  @Autowired
  EmailSenderService emailSenderServiceImpl;

  @GetMapping("/test")
  public String sendMail(){
    return "/sendMailController/mail-test";
  }

  //test sendMail Service
  @PostMapping("/send")
  public String SendMail2() throws MessagingException {
    System.out.println("entryy 999");
    Email email = new Email();
    email.setSubject("test mail service");
    email.setFrom("Me");
    email.setTo("longnthe141142@fpt.edu.vn");
    email.setTemplate("/emailTemplates/email");
    System.out.println("entryy 999");
    System.out.println(email.getFrom()+email.getTo());
    emailSenderServiceImpl.sendHtmlMessage(email);
    return "redirect:/sendMail/test";
  }

}
