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
@Table(name = "job_post")
public class JobPost {
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
    @JoinColumn(name = "job_post_id")
    private Major major;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    @OneToMany(mappedBy = "jobPost")
    private List<JobRule> jobRules;

    @OneToMany(mappedBy = "jobPost")
    private List<JobBenefit> jobBenefits;

    @OneToMany(mappedBy = "jobPost")
    private List<JobSkill> jobSkills;


    public JobPost(Company company, double salary) {
        this.company = company;
        this.salary = salary;
    }


    public JobPost(){}

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

    public List<JobRule> getMajorRules() {
        return jobRules;
    }

    public void setMajorRules(List<JobRule> jobRules) {
        this.jobRules = jobRules;
    }

    public List<JobBenefit> getJobBenefits() {
        return jobBenefits;
    }

    public void setJobBenefits(List<JobBenefit> jobBenefits) {
        this.jobBenefits = jobBenefits;
    }

    public List<JobSkill> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(List<JobSkill> jobSkills) {
        this.jobSkills = jobSkills;
    }
}
