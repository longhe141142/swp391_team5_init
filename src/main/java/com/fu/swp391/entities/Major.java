package com.fu.swp391.entities;


import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "majors")
public class Major implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "name", nullable = false, unique = true)
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
}
