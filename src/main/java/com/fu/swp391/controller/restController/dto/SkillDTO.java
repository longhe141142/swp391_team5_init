package com.fu.swp391.controller.restController.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
public class SkillDTO {
    @NotEmpty
    @NotNull
    private Long id;


    @NotEmpty
    @NotNull
    private String name;


    @NotEmpty
    private String title;



    public SkillDTO() {
    }

    public SkillDTO(Long id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public SkillDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
