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


    @NotNull
    @NotEmpty
    private Integer age;

    @Size(max = 10, min = 10, message = "Mobile number should be from 9 to 12 digits")
    @Pattern(regexp = "[0-9]{9,12}" ,message = "Phone number is invalid" )
    @NotNull
    @NotEmpty
    private String phoneNumber;

    private String name;

    @OneToMany(mappedBy = "candidate")
    @JsonIgnore
    private List<CV> cv;

    @NotNull
    @NotEmpty
    private Date dob;




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
