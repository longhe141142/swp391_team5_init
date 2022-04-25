package com.fu.swp391.entities;

import com.fu.swp391.common.enumConstants.messageEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cv")
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_upload")
    @NotNull
    @NotEmpty
    private String imageUpload;

    @NotNull
    @NotEmpty
    private int experience;

    @Column(length = 1600)
    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    @NotEmpty
    private String certificate;



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private Candidate candidate;

    @NotNull
    @NotEmpty
    private String email;


    @Size(max = 10, min = 10, message = messageEnum.PHONE_NUMBER_ERR_SIZE)
    @Pattern(regexp = "[0-9]{9,12}" ,message = messageEnum.PHONE_NUMBER_ERR_RULE)
    @NotNull
    @NotEmpty
    private String phone;

    private Date dob;

    @NotNull
    @NotEmpty
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @NotEmpty
    private String gender;

    @NotNull
    @NotEmpty
    private String purpose;



    @OneToMany(mappedBy = "cv")
    private List<SkillCV> skills;

    @OneToMany(mappedBy = "cv")
    private List<ProjectCV> projects;

    @OneToMany(mappedBy = "cv")
    private List<EducateCV> educate;

    @OneToMany(mappedBy = "cv")
    private List<ExperienceCV> experiences;

    @OneToMany(mappedBy = "cv")
    private List<CertificateCV> certificates;



    public CV(String imageURL, int experience, String content) {
        this.imageUpload = imageURL;
        this.experience = experience;
        this.content = content;
    }


    public CV(){}

    public String getImageUpload() {
        return imageUpload;
    }

    public void setImageUpload(String imageUpload) {
        this.imageUpload = imageUpload;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public List<SkillCV> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillCV> skills) {
        this.skills = skills;
    }

    public List<ProjectCV> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectCV> projects) {
        this.projects = projects;
    }

    public List<EducateCV> getEducate() {
        return educate;
    }

    public void setEducate(List<EducateCV> educate) {
        this.educate = educate;
    }

    public String getImageURL() {
        return imageUpload;
    }

    public void setImageURL(String imageURL) {
        this.imageUpload = imageURL;
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
//
//    @OneToMany(mappedBy = "cv")
//    @JsonIgnore
//    private List<CVSkill> cvSkills;


}
