package com.fu.swp391.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMajorLevel() {
        return majorLevel;
    }

    public void setMajorLevel(String majorLevel) {
        this.majorLevel = majorLevel;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMaxCandidate() {
        return maxCandidate;
    }

    public void setMaxCandidate(int maxCandidate) {
        this.maxCandidate = maxCandidate;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<MajorRule> getMajorRules() {
        return majorRules;
    }

    public void setMajorRules(List<MajorRule> majorRules) {
        this.majorRules = majorRules;
    }

    public List<MajorBenefit> getMajorBenefits() {
        return majorBenefits;
    }

    public void setMajorBenefits(List<MajorBenefit> majorBenefits) {
        this.majorBenefits = majorBenefits;
    }

    public List<MajorSkill> getMajorSkills() {
        return majorSkills;
    }

    public void setMajorSkills(List<MajorSkill> majorSkills) {
        this.majorSkills = majorSkills;
    }
}
