package com.fu.swp391.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "experience_cv")
public class ExperienceCV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    @Column(name = "job_title")
    @NotEmpty
    @NotNull
    private String jobTitle;

    @Column(name = "company_name")
    @NotEmpty
    @NotNull
    private String companyName;


    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne(optional = false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "cv_id")
    CV cv;
}
