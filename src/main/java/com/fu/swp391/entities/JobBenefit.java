package com.fu.swp391.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "job_benefits")
public class JobBenefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String title;

    @NotEmpty
    @NotNull
    private String benefit;

    @ManyToOne(optional = false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;
}
