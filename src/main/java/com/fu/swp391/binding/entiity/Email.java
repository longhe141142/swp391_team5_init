package com.fu.swp391.binding.entiity;

import java.util.HashMap;
import java.util.Map;

public class Email {

  String to;
  String from;
  String subject;
  String text;
  String template;
  private Map<String, Object> props;


  public Email(String to, String from, String subject, String text, String template) {
    this.to = to;
    this.from = from;
    this.subject = subject;
    this.text = text;
    this.template = template;
  }

  public Email() {

  }

  public void setProps(Map<String, Object> props) {
    this.props = props;
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

  Map<String, Object> properties = new HashMap<>();

  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  public void printClass(){
    System.out.println("Properties:\n");
    if (!this.properties.isEmpty()){
       System.out.println(this.properties.keySet());
       for (Object o: this.properties.values()){
         System.out.println(o);
       }
    }
    System.out.println("Email:"+""
        + "mail:"+ this.getTemplate()+"\n"
        + "mail to:"+ this.getTo());


  }
}
