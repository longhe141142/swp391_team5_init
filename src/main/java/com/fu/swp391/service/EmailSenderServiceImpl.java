package com.fu.swp391.service;

import com.fu.swp391.binding.entiity.Email;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
  @Autowired
   JavaMailSender emailSender;
  @Autowired
   SpringTemplateEngine templateEngine;

  @Override
  public void sendHtmlMessage(Email email) throws MessagingException {
    System.out.println("Email:"+""
        + "mail:"+ email.getTemplate()+"\n"
        + "mail to:"+ email.getTo());

    MimeMessage message = emailSender.createMimeMessage();
    System.out.println("1");
    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
    Context context = new Context();
//    context.setVariables(email.getProperties());
//    String s1 = new String("ab");
//    String s12 = new String("ab");
//    context.setVariable("example","abc");
//    context.setVariable("example2","abc");
//    context.setVariable("example3","abc");


//    System.out.println("2");
    helper.setFrom(email.getFrom());
    helper.setTo(email.getTo());
    helper.setSubject(email.getSubject());
    String html = templateEngine.process(email.getTemplate(), context);
//    String html = templateEngine.process(email.getTemplate(), context);

    helper.setText(html, true);
    System.out.println("Sending email: {} with html body: {}\n"
        + "email: "+email+"\n"
        + "html:" +html);
    try {
      emailSender.send(message);
      System.out.println("Thành công");

    }catch (Exception e){
      e.printStackTrace();
      System.out.println("That bai");

    }

  }
}