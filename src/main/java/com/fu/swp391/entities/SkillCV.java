package com.fu.swp391.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "skills_cv")
public class SkillCV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private int rate;

    @NotEmpty
    @NotNull
    private String description;


    @ManyToOne(optional=false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "cv_id",nullable = false, updatable = false)
    CV cv;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }
}
