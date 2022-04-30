package com.fu.swp391.controller;

import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.entities.*;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.JobPost;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    CompanyMajorService companyMajorService;

    @Autowired
    HelperUntil<Company> helperUntilCompany;
    public CandidateController(CandidateService candidateService,CompanyService companyService,CvService cvService,CompanyMajorService companyMajorService) {
        this.candidateService = candidateService;
        this.companyService = companyService;
        this.cvService = cvService;
        this.companyMajorService = companyMajorService;
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
    public String home1() {
        return "/candidate/about-us";
    }

    @GetMapping("/contact-us")
    public String home2() {
        return "/candidate/contact-us";
    }


    @GetMapping("/listAllCV/{id}")
    public String listAllCV(Model model, @PathVariable(value = "id")Long id) {
        List<CV> cvList = cvService.getAllCVById(id);
        List<CV> cvList1 = cvService.getAllCV();
        List<CV> cvListSkill = cvService.getAllCVSkill();
        Candidate candidate = candidateService.getCandidate();
        List<ExperienceCV> experienceCVList = cvService.getExperienceCVById(id);
        List<EducateCV> educateCVList1 = cvService.getEducateCVById(id);

        //List<skillFake> skillFakes =  cvService.getSkillFake();
        model.addAttribute("listCandidateCV", cvList);
        model.addAttribute("listCandidateCV1", cvList1);
        model.addAttribute("listCandidateCVSkill", cvListSkill);
        model.addAttribute("candidate", candidate);
        model.addAttribute("experienceCVList", experienceCVList);
        model.addAttribute("educateCVList1", educateCVList1);
        //model.addAttribute("skillFakes",skillFakes);
        return "/candidate/listAllCV";
    }

    @GetMapping("/detailOneCV/{id}")
    public String detailOneCV(@PathVariable(value = "id") long id, Model model) {
        List<ExperienceCV> experienceCVList = cvService.getExperienceCVById(id);
        List<EducateCV> educateCVList1 = cvService.getEducateCVById(id);
        List<CertificateCV> certificateCVS = cvService.getCertificateCVById(id);
        List<SkillCV> skillCVList = cvService.getSkillCVById(id);
        Candidate candidate = candidateService.getCandidate();

        model.addAttribute("experienceCVList", experienceCVList);
        model.addAttribute("educateCVList1", educateCVList1);
        model.addAttribute("certificateCVS", certificateCVS);
        model.addAttribute("skillCVList", skillCVList);
        model.addAttribute("candidate", candidate);
        return "/candidate/detailOneCV";
    }


    @GetMapping("/createCV")
    public String createCV(Model model) {
        Candidate candidate = candidateService.getCandidate();
        model.addAttribute("candidate", candidate);

        CV cv = new CV();
        model.addAttribute("cv", cv);
        return "/candidate/CreateCV";
    }


    @GetMapping("/delete/{id}")
    public String deleteCV(Model model, @PathVariable(value = "id") long id) {
        cvService.deleteCVById(id);
        return "redirect:/candidate/listAllCV";
    }


    @PostMapping("/postCV")
    public String PostCV(Model model) {

        return "/candidate/CreateCV";
    }


    @GetMapping("/ListCompanyCandidate")
    public String ListCompanyCandidate(Model model) {

        List<Company> ListCompany = companyService.findAllCompany();
        model.addAttribute("ListCompany",ListCompany);
        return "candidate/listCompany";
    }

    @GetMapping("/ListCompanyCandidate/{id}")
    public String ListCompanyCandidatePage(Model model,@PathVariable int id,@RequestParam(value = "searchBy", required = false) String searchBy) {
        ArrayList<Company> listcomp = companyService.findAllCandidatesByFilter(searchBy);
       ArrayList<Company> listcompanypage = helperUntilCompany.PagingElement(listcomp,id,5);
       int size = listcomp.size();
       int sopage = 0;
       if ((size%5)!=0){
           sopage = size/5 + 1;
       }else{
           sopage = size/5;
       }
       ArrayList<Integer> listsopage = new ArrayList<>();
       for (int i=0;i<sopage;i++){
           listsopage.add(i+1);
       }
       model.addAttribute("search",searchBy);
       model.addAttribute("ListCompany",listcompanypage);
       model.addAttribute("ListPage",listsopage);


        return "candidate/listCompany";
    }

    @GetMapping("/DetailCompany/{id}")
    public String DetailCompany(Model model, @PathVariable long id) {
        List<JobPost> ListCompanyDetail = companyMajorService.findCompanyMajorsByCompanyId(id);
        model.addAttribute("ListCompanyDetail",ListCompanyDetail);

        List<String> majorList = companyMajorService.findMajorbycompanyid(id);
        model.addAttribute("majorlist",majorList);

        Optional<Company> company = companyService.findbyId(id);
        model.addAttribute("company",company);
        return "candidate/detailCompany";
    }
}