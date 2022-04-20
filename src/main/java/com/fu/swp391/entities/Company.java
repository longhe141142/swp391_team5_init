package com.fu.swp391.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

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

    @Column
    @NotNull
    @NotEmpty
    @Size(max = 10, min = 10, message = "Mobile number should be from 9 to 12 digits")
    @Pattern(regexp = "[0-9]{9,12}" ,message = "Phone number is invalid" )
    private String phone;

    @Column
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Column(name = "personnel_size")
    @Email
    @NotEmpty
    @NotNull
    private int personnelSize;

    @Column(name = "founding_at")
    @NotEmpty
    @NotNull
    private Date foundingAt;

    @NotEmpty
    @NotNull
    private String description;

    @Column(name = "company_intro")
    @NotEmpty
    @NotNull
    private String companyIntro;

    @NotEmpty
    @NotNull
    private String address;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
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
