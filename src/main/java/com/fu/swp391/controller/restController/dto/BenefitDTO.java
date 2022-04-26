package com.fu.swp391.controller.restController.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data

public class BenefitDTO {
    @NotEmpty
    @NotNull
    private Long id;

    @NotEmpty
    @NotNull
    private String title;

    @NotEmpty
    @NotNull
    private String benefit;
    public BenefitDTO(){

    }
    public BenefitDTO(Long id, String title, String benefit) {
        this.id = id;
        this.title = title;
        this.benefit = benefit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }
}
