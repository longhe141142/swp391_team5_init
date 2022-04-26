package com.fu.swp391.binding.entiity;

import java.util.Map;
import lombok.Data;

public class Email {
  String to;
  String from;
  String subject;
  String text;
  String template;

  public Email(String to, String from, String subject, String text, String template) {
    this.to = to;
    this.from = from;
    this.subject = subject;
    this.text = text;
    this.template = template;
  }

  public Email(){

  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  Map<String, Object> properties;

  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }
}
