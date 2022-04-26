package com.fu.swp391.controller;

import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.JobPost;
import com.fu.swp391.repository.JobPostRepository;
import com.fu.swp391.service.CompanyMajorService;
import com.fu.swp391.service.CompanyService;
import java.util.List;

import com.fu.swp391.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyMajorService companyMajorService;

    @Autowired
    JobPostService jobPostService;

    @Autowired
    JobPostRepository jobPostRepository;

    public CompanyController(CompanyService companyService, CompanyMajorService companyMajorService) {
        this.companyService = companyService;
        this.companyMajorService = companyMajorService;
    }

    @GetMapping("/home")
    public String home(){
        return "/company/HomeCompany";
    }
    @GetMapping("/listjob")
    public String listAllJob(Model model){


        model.addAttribute("Jobs", jobPostRepository.findJobPostByCompanyId(1));

        return "/company/ListAllJob";
    }
    @GetMapping("/jobpost/view/{id}")
    public String jobDetail(Model model, @PathVariable("id") long id){

        model.addAttribute("job_detail", jobPostRepository.findJobPostById(id));
        return "/company/JobDetail";
    }
    @GetMapping("/ListCompanyAdmin12")
    public String ListCompanyAdmin(Model model) {

        List<Company> list = companyService.findAllCompany();
        model.addAttribute("ListCompany", list);
        System.out.println("Size_of_company: "+list.size());
        return "company/ListAllCompany";
    }

    @GetMapping("/DetailCompany12/{id}")
    public String DetailCompany(Model model, @PathVariable long id) {
        List<JobPost> ListCompanyDetail = companyMajorService.findCompanyMajorsByCompanyId(id);
        model.addAttribute("ListCompanyDetail12",ListCompanyDetail);
        return "company/ListAllCompany";
    }

    @GetMapping("/ListCvRequest")
    public String ListRequest(Model model) {
        return "company/ListRequest";
    }
}

