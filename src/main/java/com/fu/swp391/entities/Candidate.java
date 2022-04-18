package com.fu.swp391.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidates")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String gender;

    public Candidate(String gender, int age, String phoneNumber, String name, Date dob) {
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.dob = dob;
    }

    public Candidate(){}

    @NotNull
    private int age;

    @Size(max = 10, min = 10, message = "Mobile number should be from 9 to 12 digits")
    @Pattern(regexp = "[0-9]{9,12}" ,message = "Phone number is invalid" )
    @NotNull
    @NotEmpty
    private String phoneNumber;

    private String name;

    @OneToMany(mappedBy = "candidate")
    @JsonIgnore
    private List<CV> cv;

    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Date dob;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CV> getCv() {
        return cv;
    }

    public void setCv(List<CV> cv) {
        this.cv = cv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String avatar;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }


}
