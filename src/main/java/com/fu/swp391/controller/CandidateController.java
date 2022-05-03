package com.fu.swp391.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fu.swp391.binding.entiity.Email;
import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.entities.*;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.JobPostRepository;
import com.fu.swp391.repository.RequestRepository;
import com.fu.swp391.service.*;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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
    JobPostRepository jobPostRepository;


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
    EmailSenderService emailSenderServiceImpl;

    @Autowired
    RequestService requestService;

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
    @GetMapping("/jobdetail/{id}")
    public String getJobDetail(Model model, @PathVariable("id") long id){
        model.addAttribute("job_detail",jobPostRepository.findJobPostById(id));
        return "candidate/JobDetail";
    }

    @GetMapping("/chooseCVRequest/{id}")
    public String ChooseCV(Model model, @PathVariable("id") long id){
        JobPost job = jobPostRepository.findJobPostById(id);

        User user = userService.findByEmail(helperUntilCompany.getPrincipal());
        user.getCandidates().get(0).getId();

        List<CV> cv = cvService.findCVByCandidate(user.getCandidates().get(0));

        model.addAttribute("list_cv",cv);
        model.addAttribute("job_id",id);
        return "candidate/CvSendReq";
    }

    @GetMapping("/sendCVRequest")
    public @ResponseBody String sendCVRequest(HttpServletRequest request) throws MessagingException {



        String comment = request.getParameter("comment");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        long cv_id = Long.parseLong(request.getParameter("cv_id"));
        long job_id = Long.parseLong(request.getParameter("job_id"));

        CV cv = cvService.findCVById(cv_id);
        JobPost jp = jobPostRepository.findJobPostById(job_id);

        Request r = new Request();

        r.setComment(comment);
        r.setContent(content);
        r.setStatus("SENT");
        r.setSubject(subject);
        r.setCvImage(cv.getImageUpload());
        r.setFromUser(cv.getCandidate().getUser());
        r.setToUser(jp.getCompany().getUser());
        r.setCv(cv);
        r.setJobPost(jp);

        requestRepository.save(r);

        Email email = new Email();

        email.setSubject(subject);
        email.setFrom(cv.getCandidate().getUser().getEmail());
        email.setTo(jp.getCompany().getEmail());
        email.setText(content);
        email.setTemplate("/emailTemplates/emailRequestCv");


        emailSenderServiceImpl.sendMailRequestCV(email,comment,jp.getCompany().getName(),cv.getCandidate().getUser().getEmail());

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString("");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
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