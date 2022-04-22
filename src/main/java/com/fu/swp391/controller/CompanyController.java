package com.fu.swp391.controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("company")
public class CompanyController {

    @GetMapping("/home")
    public String home(){
        return "/company/index";
    }
}
=======
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.CompanyMajor;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyMajorService;
import com.fu.swp391.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("company12")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyMajorService companyMajorService;

    public CompanyController(CompanyService companyService, CompanyMajorService companyMajorService) {
        this.companyService = companyService;
        this.companyMajorService = companyMajorService;
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
        List<CompanyMajor> ListCompanyDetail = companyMajorService.findCompanyMajorsByCompanyId(id);
        model.addAttribute("ListCompanyDetail12",ListCompanyDetail);
        return "company/ListAllCompany";
    }
}

>>>>>>> 1f0453f (Duc_4_22_2022)
