package com.fu.swp391.entities;

import lombok.AllArgsConstructor;
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
@Table(name = "certificate_cv")
public class CertificateCV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "certificate_name")
    @NotEmpty
    @NotNull
    private String certificateName;

    @Column(name = "organization")
    @NotEmpty
    @NotNull
    private String organization;

    @Column(name = "graduation_date")
    private Date graduationDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "cv_id", nullable = false)
    private CV cv;

}
