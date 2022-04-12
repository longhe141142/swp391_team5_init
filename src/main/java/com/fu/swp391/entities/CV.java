package com.fu.swp391.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cv")
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String imageURL;

    @NotNull
    @NotEmpty
    private int experience;

    @NotNull
    @NotEmpty
    private String content;

    public CV(String imageURL, int experience, String content) {
        this.imageURL = imageURL;
        this.experience = experience;
        this.content = content;
    }

    public CV(){}

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(mappedBy = "cv")
    @JsonIgnore
    private List<CVSkill> cvSkills;


}
