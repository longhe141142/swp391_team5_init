package com.fu.swp391.controller;

import com.fu.swp391.binding.entiity.PagingParam;
import com.fu.swp391.common.enumConstants.PagingParameter;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.JobPost;
import com.fu.swp391.entities.User;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.JobPostRepository;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyMajorService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.JobPostService;
import com.fu.swp391.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyMajorService companyMajorService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    JobPostService jobPostService;

    @Autowired
    JobPostRepository jobPostRepository;

    @Autowired
    HelperUntil<Candidate> candidateHelperUntil;

    @Autowired
    UserService userService;

    public CompanyController(CompanyService companyService,
        CompanyMajorService companyMajorService) {
        this.companyService = companyService;
        this.companyMajorService = companyMajorService;
    }

    @GetMapping("/listjob")
    public String listAllJob(Model model) {

        model.addAttribute("Jobs", jobPostRepository.findJobPostByCompanyId(1));

        return "/company/ListAllJob";
    }

    @GetMapping("/jobpost/view/{id}")
    public String jobDetail(Model model, @PathVariable("id") long id) {

        model.addAttribute("job_detail", jobPostRepository.findJobPostById(id));
        return "/company/JobDetail";
    }
    @GetMapping("/ListCompanyAdmin12")
    public String ListCompanyAdmin(Model model) {

        List<Company> list = companyService.findAllCompany();
        model.addAttribute("ListCompany", list);
        System.out.println("Size_of_company: " + list.size());
        return "company/ListAllCompany";
    }

    @GetMapping("/DetailCompany12/{id}")
    public String DetailCompany(Model model, @PathVariable long id) {
        List<JobPost> ListCompanyDetail = companyMajorService.findCompanyMajorsByCompanyId(id);
        model.addAttribute("ListCompanyDetail12", ListCompanyDetail);
        return "company/ListAllCompany";
    }


    @GetMapping("home")
    public String renderCompanyHome(@RequestParam(value = "page", required = false) Integer page
        , Model model) {
        ArrayList<Candidate> candidates = candidateService.findAllCandidates();
        int pageIndex = page != null ? page : 1;
        int sizeDef = PagingParameter.PAGE_SIZE_COMPANY;

        String currentUserEmail = candidateHelperUntil.getPrincipal();
        User u = userService.findByEmail(currentUserEmail);
        Company yourCompany = u.getCompanies().get(0);
        System.out.println(yourCompany.toString());
        PagingParam pagingParam = new PagingParam(
            candidateHelperUntil.getTotalSize(candidates.size(), sizeDef),
            candidates.size(), sizeDef, pageIndex);
        ArrayList<Candidate> getCandidatesByPaging = candidateService.getAllCandidateByPaging(
            candidates, pageIndex, sizeDef);
        pagingParam.setTotalElementInCurrentPage(getCandidatesByPaging.size());
        model.addAttribute("pageParam", pagingParam);
        model.addAttribute("candidates",getCandidatesByPaging);
        model.addAttribute("company",yourCompany);

        return "/company/company-home/home";
    }


}

