package com.fu.swp391.entities;

import javax.persistence.*;

@Entity
@Table(name = "cv_skill")
public class CVSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cv_id")
    private CV cv;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private Skill skill;

}
