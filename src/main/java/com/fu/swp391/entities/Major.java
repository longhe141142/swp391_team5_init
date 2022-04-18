package com.fu.swp391.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity()
@Table(name = "majors")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String description;

    @Column(nullable = false, unique = true)
    @NotEmpty
    @NotNull
    private String name;

    @ManyToMany
    @JoinTable(
            name = "major_skill",
            joinColumns = @JoinColumn(name = "major_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    Set<Skill> skills;

    @OneToMany(mappedBy = "major")
    private List<CompanyMajor> companyMajors;

}
