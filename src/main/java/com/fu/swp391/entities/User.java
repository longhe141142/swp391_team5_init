package com.fu.swp391.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @NotEmpty
    @Size(min = 6, message = "Password should be more than 5 letters" )
    private String password;

    @NotNull
    @NotEmpty
    private String gender;

    @NotNull
    @NotEmpty
    private String status;


    public static PasswordEncoder getPasswordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public User(String email, String password, String gender, String status, Integer age, String phoneNumber, String name, Date birthDate, String token, String passwordToken, String avatar) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.token = token;
        this.passwordToken = passwordToken;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Candidate> candidates = new ArrayList<>();


    @Transient
    private String token;

    private String passwordToken;//for password recovery

    public void setCandidate(Candidate candidate) {
        this.candidates.add(candidate);
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public User(){}
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

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
