package com.fu.swp391.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "company_major")
public class CompanyMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "salary")
    private double salary;

    @Column(name="major_level")
    @NotEmpty
    @NotEmpty
    private String majorLevel;

    @Column(name = "major_name")
    @NotEmpty
    @NotEmpty
    private String majorName;


    @Column(name = "work_address")
    @NotEmpty
    @NotEmpty
    private String workAddress;

    @Column(name = "gender")
    @NotEmpty
    @NotEmpty
    private String gender;

    @Column(name = "max_candidates")
    @NotEmpty
    @NotEmpty
    private int maxCandidate;

    @NotEmpty
    @NotEmpty
    private String experience;

    @Column(name = "job_description")
    @NotEmpty
    @NotEmpty
    private String jobDescription;

    @ManyToOne(optional = false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "companyMajor")
    private List<MajorRule> majorRules;

    @OneToMany(mappedBy = "companyMajor")
    private List<MajorBenefit> majorBenefits;

    @OneToMany(mappedBy = "companyMajor")
    private List<MajorSkill> majorSkills;


    public CompanyMajor(Company company, double salary) {
        this.company = company;
        this.salary = salary;
    }


    public CompanyMajor(){}

    public Company getCompanyInfo() {
        return company;
    }

    public void setCompanyInfo(Company company) {
        this.company = company;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


}
