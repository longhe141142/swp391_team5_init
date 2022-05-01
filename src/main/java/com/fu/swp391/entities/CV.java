package com.fu.swp391.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fu.swp391.common.enumConstants.messageEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cv")
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_upload")
    private String imageUpload;

    private int experience;

    @Column(length = 1600)
    private String content;

    private String certificate;



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    private String email;


    @Size(max = 10, min = 10, message = messageEnum.PHONE_NUMBER_ERR_SIZE)
    @Pattern(regexp = "[0-9]{9,12}" ,message = messageEnum.PHONE_NUMBER_ERR_RULE)
    private String phone;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "dob")
    private Date dob;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String gender;

    private String purpose;



    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
    private List<SkillCV> skills = new ArrayList<>();

    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
    private List<ProjectCV> projects = new ArrayList<>();

    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
    private List<EducateCV> educate = new ArrayList<>();

    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
    private List<ExperienceCV> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
    private List<CertificateCV> certificates = new ArrayList<>();




    public CV(String imageURL, int experience, String content) {
        this.imageUpload = imageURL;
        this.experience = experience;
        this.content = content;
    }

    public List<ExperienceCV> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceCV> experiences) {
        this.experiences = experiences;
    }

    public List<CertificateCV> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateCV> certificates) {
        this.certificates = certificates;
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

    public List<ExperienceCV> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceCV> experiences) {
        this.experiences = experiences;
    }

    public List<CertificateCV> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateCV> certificates) {
        this.certificates = certificates;
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

    //LONG HE141142
    public void setProject(ProjectCV project) {
        if (this.projects == null) {
            this.projects = new ArrayList<>();
        }
        this.projects.add(project);
    }

    public void setSkill(SkillCV skill) {
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        this.skills.add(skill);
    }

    public void setOneEducate(EducateCV educate) {
        if (this.educate == null) {
            this.educate = new ArrayList<>();
        }
        this.educate.add(educate);
    }


    public void setExperience(ExperienceCV experienceCV) {
        if (this.experiences == null) {
            this.experiences = new ArrayList<>();
        }
        this.experiences.add(experienceCV);
    }

    public void setCertificate(CertificateCV certificateCV) {
        if (this.certificates == null) {
            this.certificates = new ArrayList<>();
        }
        this.certificates.add(certificateCV);
    }

    //LONG HE141142 END

}
