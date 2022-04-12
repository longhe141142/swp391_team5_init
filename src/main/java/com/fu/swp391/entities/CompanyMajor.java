package com.fu.swp391.entities;

import javax.persistence.*;

@Entity
@Table(name = "company_major")
public class CompanyMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_info_id")
    private CompanyInfo companyInfo;

    @Column(name = "salary")
    private double salary;

    public CompanyMajor(CompanyInfo companyInfo, double salary) {
        this.companyInfo = companyInfo;
        this.salary = salary;
    }

    public CompanyMajor(){}

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
