package com.fu.swp391.controller.restController.dto;

import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.User;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class addCompany {
    @Valid
    @NotNull
    public User user;

    @Valid
    @NotNull
    public Company company;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
