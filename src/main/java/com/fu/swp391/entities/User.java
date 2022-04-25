package com.fu.swp391.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Table(name = "users")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

  private String passwordEncoder;

  public void setPasswordEncoder(String hashed) {
    this.passwordEncoder = hashed;
  }



    private String status;

  public String getPasswordEncoder() {
    return this.passwordEncoder;
  }

  public List<Candidate> getCandidates() {
    return candidates;
  }

  public void setCandidates(List<Candidate> candidates) {
    this.candidates = candidates;
  }

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public User(String email, String status, Integer age, String phoneNumber, String name, Date birthDate, String token, String passwordToken, String avatar) {
        this.email = email;
        this.status = status;
        this.token = token;
        this.resetPasswordToken = passwordToken;
    }

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  Set<Role> roles = new HashSet<Role>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Candidate> candidates = new ArrayList<>();

    @Transient
    private String token;

    @Column(name = "password_token")
    private String resetPasswordToken;

    public void setCandidate(Candidate candidate) {
        this.candidates.add(candidate);
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }


  public User() {
    this.roles = new LinkedHashSet<Role>();
  }
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    private List<WebReview> comments;
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public List<WebReview> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<WebReview> comments) {
//        this.comments = comments;
//    }


    @Override
    public String toString() {
        return "UserModel{" +
                "email='" + email + '\'' +
                "," +
                '}';
    }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
    }

  public void setRole(Role _role) {
    this.roles.add(_role);
  }

    public Long getId() {
        return id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
