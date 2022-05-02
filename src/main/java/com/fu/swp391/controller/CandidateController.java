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
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.RequestRepository;
import com.fu.swp391.repository.CandidateRepository;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyMajorService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.CvService;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

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

    @Autowired
    HelperUntil<Candidate> candidateHelperUntil;

    @Autowired
    RequestRepository requestRepository;



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
        ArrayList<Company> listcomp = companyService.findAllCandidatesByFilter(searchBy);
        Collections.sort(listcomp, new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                if(o1.getName().compareTo(o2.getName())>0){
                    return 1;
                }
                return -1;
            }
        });
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

    @GetMapping("/request-detail")
    public String requestDetail(@RequestParam(value = "id",required = true) Long id,Model model)
        throws Exception {
        Optional<Request> request = requestRepository.findById(id);
        if (!request.isPresent()){
            throw new Exception("Request doesn't exist!");
        }
//        Optional<User> company =  userService.findById(request.get().getFromUser());
         model.addAttribute("request",request.get());
        return "/request/request-detail";
    }

    @GetMapping("/profileCandidate")
    public String profileCandidate(Model model) {
String email = getPrincipal();
Optional<Candidate> candidate = candidateService.getcandidatebyEmail(email);
model.addAttribute("email",email);
model.addAttribute("candidate",candidate);
        return "candidate/Profilecandidate";
    }
    @GetMapping("/editprofile")
    public String editcandidateprofile(Model model) {
        String email = getPrincipal();
        Optional<Candidate> candidate = candidateService.getcandidatebyEmail(email);
        Candidate a = candidate.get();
        System.out.println(a.getName());
        model.addAttribute("email",email);
        model.addAttribute("candidate",a);
        return "candidate/editProfile";
    }
    @PostMapping("editCandidate")
    public String editcandidate(@Validated @ModelAttribute("Candidate") Candidate candidate, BindingResult result,
                                @RequestParam Long id, @RequestParam("photo")MultipartFile photo){
//        if (result.hasErrors()){
//            List<FieldError> fields = result.getFieldErrors();
//            for (int i =0;i<fields.size();i++){
//                System.out.println("error field name:"+fields.get(i).getField()+
//                        "\nError message: "+fields.get(i).getDefaultMessage());
//            }
//            System.out.println("12345678");
//            return "redirect:/admin/loadCompanyToEdit?id="+id;
//        }
        candidate.setAvatar(photo.getOriginalFilename());


        candidateRepository.updateCandidate(id,candidate.getAvatar(),candidate.getPhoneNumber(), candidate.getDob());
        System.out.println("123456");
        return "redirect:/candidate/home";

    }



}