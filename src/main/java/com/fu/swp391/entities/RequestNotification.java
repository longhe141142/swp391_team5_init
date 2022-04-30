package com.fu.swp391.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class RequestNotification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  String title;

  @Column(name="detail")
  String detail;

  @Column(name="is_seen")
  Boolean isSeen;

  @Column(name = "is_send")
  Boolean isSend;

 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 private User user;

}