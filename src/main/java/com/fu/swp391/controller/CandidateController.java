package com.fu.swp391.controller;

import com.fu.swp391.common.enumConstants.GenderEnum;
<<<<<<< HEAD
import com.fu.swp391.entities.*;
import com.fu.swp391.repository.ExperienceRepository;
=======
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.CompanyMajor;
>>>>>>> 0bf80d9 (Thêm thymeleaf)
import com.fu.swp391.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
=======
>>>>>>> 0bf80d9 (Thêm thymeleaf)
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("candidate")
public class CandidateController {
    @Autowired
    RoleService roleService;

    @Autowired
    CvService cvService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    GenderEnum genderEnum;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;
<<<<<<< HEAD

    public CandidateController(CandidateService candidateService,CompanyService companyService,CvService cvService) {
        this.candidateService = candidateService;
        this.companyService = companyService;
        this.cvService = cvService;
=======
@Autowired
CompanyMajorService companyMajorService;
    public CandidateController(CandidateService candidateService,CompanyService companyService,CompanyMajorService companyMajorService) {
        this.candidateService = candidateService;
        this.companyService = companyService;
        this.companyMajorService = companyMajorService;
>>>>>>> 0bf80d9 (Thêm thymeleaf)
    }


    //    @GetMapping("/listAllCV")
//    public String listAllCV(Model model){
//        model.addAttribute("listCandidateCV", candidateService.getAllCandidate());
//        return "candidate/listAllCV";
//    }

///candidate/HomeCandidate
    @GetMapping("/home")
    public String home(){
        return "/candidate/HomeCandidate";
    }
    @GetMapping("/about-us")
    public String home1(){
        return "/candidate/about-us";
    }
    @GetMapping("/contact-us")
    public String home2(){
        return "/candidate/contact-us";
    }


    @GetMapping("/listAllCV")
    public String listAllCV(Model model){
        List<CV> cvList =  cvService.getAllCV();
        List<CV> cvListSkill =  cvService.getAllCVSkill();
        Candidate candidate =  candidateService.getCandidate();

        //List<skillFake> skillFakes =  cvService.getSkillFake();
        model.addAttribute("listCandidateCV",cvList);
        model.addAttribute("listCandidateCVSkill",cvListSkill);
        model.addAttribute("candidate",candidate);
        //model.addAttribute("skillFakes",skillFakes);
        return "/candidate/listAllCV";
    }

    @GetMapping("/detailOneCV/{id}")
    public String detailOneCV(@PathVariable (value = "id") long id, Model model){
        List<ExperienceCV> experienceCVList = cvService.getExperienceCVById(id);
        List<EducateCV> educateCVList1 = cvService.getEducateCVById(id);
        List<CertificateCV> certificateCVS = cvService.getCertificateCVById(id);
        List<SkillCV> skillCVList = cvService.getSkillCVById(id);
        Candidate candidate =  candidateService.getCandidate();

        model.addAttribute("experienceCVList",experienceCVList);
        model.addAttribute("educateCVList1",educateCVList1);
        model.addAttribute("certificateCVS",certificateCVS);
        model.addAttribute("skillCVList",skillCVList);
        model.addAttribute("candidate",candidate);
        return "/candidate/detailOneCV";
    }


    @GetMapping("/createCV")
    public String createCV(Model model){
        Candidate candidate =  candidateService.getCandidate();
        model.addAttribute("candidate",candidate);

        CV cv = new CV();
        model.addAttribute("cv", cv);
        return "/candidate/CreateCV";
    }

    @PostMapping("/postCV")
    public String PostCV(Model model){

        return "/candidate/CreateCV";
    }



    @GetMapping("/ListCompanyCandidate")
    public String ListCompanyCandidate(Model model) {

        List<Company> ListCompany = companyService.findAllCompany();
        model.addAttribute("ListCompany",ListCompany);
        return "candidate/listCompany";
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @GetMapping("/candidatehome")
    public String homecandidate(Model model) {
        return "candidate/HomeCandidate";
=======
    @GetMapping("/detailcompany")
    public String DetailCompany(Model model) {
=======
    @GetMapping("/DetailCompany/{id}")
    public String DetailCompany(Model model, @PathVariable long id) {
//
        List<CompanyMajor> ListCompanyDetail = companyMajorService.findCompanyMajorsByCompanyId(id);
        model.addAttribute("ListCompanyDetail",ListCompanyDetail);
>>>>>>> 0bf80d9 (Thêm thymeleaf)

        return "candidate/detailCompany";
>>>>>>> 06490ba (Thêm Detail company)
    }
}
