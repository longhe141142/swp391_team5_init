package com.fu.swp391.controller.restController.dto;

import com.fu.swp391.binding.entiity.Email;
import com.fu.swp391.entities.Request;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SendRequest {


  @NotNull
  Long sendToUserId;

  @NotNull
  @NotEmpty
  String content;

  @NotNull
  @NotEmpty
  String subject;


  @NotNull
  Long cvId;

  public Long getCvId() {
    return cvId;
  }

  public void setCvId(Long cvId) {
    this.cvId = cvId;
  }

  @NotNull
  @NotEmpty
  String organizationName;

  public Long getSendToUserId() {
    return sendToUserId;
  }

  public void setSendToUserId(Long sendToUserId) {
    this.sendToUserId = sendToUserId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }


  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }
}
