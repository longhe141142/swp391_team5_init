package com.fu.swp391.controller;

import com.fu.swp391.binding.entiity.UserCandidate;
import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.entities.Company;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller("candidate")
public class CandidateController {
    @Autowired
    RoleService roleService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    GenderEnum genderEnum;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;
    @GetMapping("/ListCompanyCandidate")
    public String ListCompanyCandidate(Model model) {

        List<Company> ListCompany = companyService.findAllCompany();
        model.addAttribute("ListCompany",ListCompany);
        return "candidate/listCompany";
    }

    @GetMapping("/candidatehome")
    public String homecandidate(Model model) {



        return "candidate/HomeCandidate.html";
    }

}
