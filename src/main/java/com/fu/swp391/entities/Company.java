package com.fu.swp391.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "company_image",nullable = true)
    private String companyImageUrl;

    @Column(name = "company_status",nullable = true)
    private String status;

    @Transient
    MultipartFile image;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Column
    @Size(max = 12, min = 9, message = "Mobile number should be from 9 to 12 digits")
    @Pattern(regexp = "[0-9]{9,12}" ,message = "Phone number is invalid" )
    @NotNull
    @NotEmpty
    private String phone;

    @Column
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Column(name = "personnel_size")
    @NotNull
    private int personnelSize;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(pattern="yyyy/MM/dd")
    @Column(name = "founding_at")
    @NotNull
    private Date foundingAt;

    @Column(length = 1700)
    @NotEmpty
    @NotNull
    private String description;

    public String getCompanyImageUrl() {
        return companyImageUrl;
    }

    public void setCompanyImageUrl(String companyImageUrl) {
        this.companyImageUrl = companyImageUrl;
    }


    @Column(name = "company_intro",length = 1700)
    @NotEmpty
    @NotNull
    private String companyIntro;

    @NotEmpty
    @NotNull
    private String address;


    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPersonnelSize() {
        return personnelSize;
    }

    public void setPersonnelSize(int personnelSize) {
        this.personnelSize = personnelSize;
    }

    public Date getFoundingAt() {
        return foundingAt;
    }

    public void setFoundingAt(Date foundingAt) {
        this.foundingAt = foundingAt;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}