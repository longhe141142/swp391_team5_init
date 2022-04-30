package com.fu.swp391.controller;

import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.JobPost;
import com.fu.swp391.entities.Request;
import com.fu.swp391.entities.User;
import com.fu.swp391.repository.JobPostRepository;
import com.fu.swp391.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    UserService userService;

    @Autowired
    RequestService requestService;
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
        return "company/ListAllCompany2";
    }

    @GetMapping("/DetailCompany12/{id}")
    public String DetailCompany(Model model, @PathVariable long id) {
        List<JobPost> ListCompanyDetail = companyMajorService.findCompanyMajorsByCompanyId(id);
        model.addAttribute("ListCompanyDetail12",ListCompanyDetail);
        return "company/ListAllCompany";
    }

    @GetMapping("/ListCvRequest")
    public String ListRequest(Model model) {
        String email = getPrincipal();
        User user = userService.findByEmail(email);
        System.out.println(email);
        List<Request> ListRequest = requestService.findAllByToUserId(user.getId());
        List<JobPost> jobPostList = new ArrayList<>();
        for (Request re: ListRequest
             ) {
            re.setComment(companyMajorService.FindJobById(re.getJobPost().getId()).getMajorName());
        }
        model.addAttribute("listJob",jobPostList);
        model.addAttribute("ListRequest",ListRequest);
        Optional<Company> company =  companyService.findbyId(user.getId());
        model.addAttribute("compamny",company);
        return "company/ListRequest";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }


}

