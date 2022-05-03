package com.fu.swp391.controller;

import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.CertificateCV;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.EducateCV;
import com.fu.swp391.entities.ExperienceCV;
import com.fu.swp391.entities.JobPost;
import com.fu.swp391.entities.Request;
import com.fu.swp391.entities.SkillCV;
import com.fu.swp391.entities.User;
import com.fu.swp391.entities.*;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.RequestRepository;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyMajorService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.CvService;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    RequestRepository requestRepository;

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

    @Autowired
    HelperUntil<Candidate> candidateHelperUntil;


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
        candidateHelperUntil.getPrincipal();
        Candidate candidate = candidateService.getCandidate();
        model.addAttribute("candidate", candidate);
        CV cv = new CV();
        model.addAttribute("cv", cv);
        return "/candidate/CreateCV";
    }

    @GetMapping("/createCV2")
    public String createCV2(Model model) {
        candidateHelperUntil.getPrincipal();
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

        List<Company> ListCompany = companyService.findAllCandidatesByFilter(searchBy);
        ArrayList<Company> ListPage = helperUntilCompany.PagingElement((ArrayList<Company>) ListCompany,id,5);
        int n = ListCompany.size()/5;
        int NumberOfPage ;
        if(ListCompany.size()%5==0){
            NumberOfPage = n;
        }else {
            NumberOfPage = n+1;
        }
        ArrayList<Integer> listPaging = new ArrayList<Integer>();
        for (int i = 1;i<NumberOfPage+1;i++){
            listPaging.add(i);
        }
        model.addAttribute("ListPage",listPaging);
        model.addAttribute("ListCompany",ListPage);
        model.addAttribute("search",searchBy);
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

    @GetMapping("/request-detail")
    public String requestDetail(@RequestParam(value = "id",required = true) Long id,Model model)
        throws Exception {
        Optional<Request> request = requestRepository.findById(id);
        if (!request.isPresent()){
            throw new Exception("Request doesn't exist!");
        }
         model.addAttribute("request",request.get());
        return "/request/request-detail";
    }

    @GetMapping ("loadRequestForDetail")
    public String loadRequestForDetail(@RequestParam Long id, Model model) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        model.addAttribute("optionalRequest",optionalRequest.get());
        return "/candidate/detailRequest";
    }
    @GetMapping("editRequestAccept")
    public String editRequestAccept(HttpServletRequest request){
        String comment =  request.getParameter("comment");
        long id = Long.parseLong(request.getParameter("id"));
        String accept = request.getParameter("ACCEPT");
        System.out.println("ACC"+ accept);
        requestRepository.update(id, accept, comment);
        System.out.println("123456");
        return "redirect:/candidate/listRequestCompany";

    }
    @GetMapping("editRequestDeny")
    public String editRequestDeny(HttpServletRequest request){
        String comment =  request.getParameter("comment");
        long id = Long.parseLong(request.getParameter("id"));
        String deny = request.getParameter("DENY");
        System.out.println("ACC"+ deny);
        requestRepository.update(id, deny, comment);
        System.out.println("123456");
        return "redirect:/candidate/listRequestCompany";


    }

    @GetMapping("/listRequestCompany")
    public String listRequestCompany(Model model) {
        User user = userService.findByEmail(getPrincipal());
       // Optional<Request> rq = requestRepository.findById(user.getId());
        System.out.println("ID: "+user.getId());
        List<Request> requestcompany = requestRepository.fillAllRequestCompanyByTo_Id(user.getId());
        model.addAttribute("requestcompany",requestcompany);
        return "candidate/listRequestCompanySendCandidate";
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