package com.fu.swp391.controller;

import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.common.enumConstants.StatusEnum;
import com.fu.swp391.entities.*;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.CVRepository;
import com.fu.swp391.repository.ExperienceRepository;
import com.fu.swp391.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequest;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyMajorService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.CvService;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    CVRepository cvRepository;

    @Autowired
    HelperUntil<Company> helperUntilCompany;
    public CandidateController(CVRepository cvRepository, CandidateService candidateService,CompanyService companyService,CvService cvService,CompanyMajorService companyMajorService) {
        this.candidateService = candidateService;
        this.companyService = companyService;
        this.cvService = cvService;
        this.companyMajorService = companyMajorService;
        this.cvRepository = cvRepository;
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

    @GetMapping("/statusPublic")
    public String statusPublic(@RequestParam (value = "id") Long id) throws Exception {
        if(id == null) throw new Exception("CV not Found!!");
        Optional<CV> cv = cvRepository.findById(id);
        cv.get().setStatus(StatusEnum.PUBLIC);
        cvRepository.save(cv.get());
        return "redirect:/candidate/detailOneCV/"+id;
    }
    @GetMapping("/statusPrivate")
    public String statusPrivate(@RequestParam (value = "id") Long id) throws Exception {
        if(id == null) throw new Exception("CV not Found!!");
        Optional<CV> cv = cvRepository.findById(id);
        cv.get().setStatus(StatusEnum.PRIVATE);
        cvRepository.save(cv.get());
        return "redirect:/candidate/detailOneCV/"+id;
    }

    @GetMapping("/listAllCV/{id}")
    public String listAllCV(Model model, @PathVariable(value = "id")Long id) {
        String email = candidateHelperUntil.getPrincipal();
        Candidate can= candidateService.getCandidate(email);


        List<CV> cvList = cvService.getAllCVById(id);
        model.addAttribute("listCandidateCV", cvList);

        List<CV> cvList1 = cvService.getAllCV(can.getName());
        model.addAttribute("listCandidateCV1", cvList1);

        Candidate candidate = candidateService.getCandidate(email);
        model.addAttribute("candidate", candidate);

        List<SkillCV> skillCVList = cvService.getSkillCVById(id);
        model.addAttribute("skillCVList", skillCVList);

        List<ExperienceCV> experienceCVList = cvService.getExperienceCVById(id);
        model.addAttribute("experienceCVList", experienceCVList);

        List<EducateCV> educateCVList1 = cvService.getEducateCVById(id);
        model.addAttribute("educateCVList1", educateCVList1);


        //List<skillFake> skillFakes =  cvService.getSkillFake();
        //model.addAttribute("skillFakes",skillFakes);
        return "/candidate/listAllCV";
    }

    @GetMapping("/detailOneCV/{id}")
    public String detailOneCV(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("id",id);
        String email = candidateHelperUntil.getPrincipal();
        List<EducateCV> educateCVList1 = cvService.getEducateCVById(id);
        List<CertificateCV> certificateCVS = cvService.getCertificateCVById(id);
        List<SkillCV> skillCVList = cvService.getSkillCVById(id);
        Candidate candidate = candidateService.getCandidate(email);

        Optional<CV> cv = cvRepository.findById(id);
        model.addAttribute("cv",cv.get());

        //list Experience trong detail oneCV
        List<ExperienceCV> experienceCVList = cvService.getExperienceCVById(id);
        model.addAttribute("experienceCVList", experienceCVList);

        model.addAttribute("educateCVList1", educateCVList1);
        model.addAttribute("certificateCVS", certificateCVS);
        model.addAttribute("skillCVList", skillCVList);
        model.addAttribute("candidate", candidate);
        return "/candidate/detailOneCV";
    }

    //add new CV

    @GetMapping("/createCV")
    public String createCV(Model model) {
        String name = candidateHelperUntil.getPrincipal();
        Candidate candidate = candidateService.getCandidate(name);
        model.addAttribute("candidate", candidate);
        CV cv = new CV();
        model.addAttribute("cv", cv);
        return "/candidate/CreateCV";
    }

    @PostMapping("/postCV")
    public String saveCV(@ModelAttribute("cv") CV cv) {
        String email = candidateHelperUntil.getPrincipal();
        User user = userService.findByEmail(email);
        cv.setCandidate(user.getCandidates().get(0));
        cvService.saveCV(cv);
        return "redirect:/candidate/home";
    }


    @GetMapping("/createCV2")
    public String createCV2(Model model) {
//        String name = candidateHelperUntil.getPrincipal();
//        Candidate candidate = candidateService.getCandidate(name);
//        model.addAttribute("candidate", candidate);
        CV cv = new CV();
        model.addAttribute("cv", cv);
        return "/candidate/CreateCV";
    }




    @GetMapping("/delete/{id}")
    public String deleteCV(Model model, @PathVariable(value = "id") long id) {
        cvService.deleteCVById(id);
        return "redirect:/candidate/listAllCV/"+id+"";
    }

    @GetMapping("/editCV/{id}")
    public String editCV(Model model, @PathVariable(value = "id") long id) {
        String email = candidateHelperUntil.getPrincipal();
        Candidate candidate = candidateService.getCandidate(email);
        model.addAttribute("candidate1",candidate);

        CV cv = cvService.listOneAllCVById(id);
        model.addAttribute("cv",cv);

//        CV cv = new CV();
//        model.addAttribute("cv",cv);
//        cvService.deleteCVById(id);
        return "/candidate/editCV";
    }


    @PostMapping("/saveEditCV/{id}")
    public String saveEditCV(@PathVariable long id, @ModelAttribute("cv") CV cv,Model model) {
        //get cv by id
        CV cvList = cvService.listOneAllCVById(id);
        cvList.setId(id);
        cvList.setPhone(cv.getPhone());
        cvList.setDob(cv.getDob());
        cvList.setGender(cv.getGender());
        cvList.setEmail(cv.getEmail());
        cvList.setPurpose(cv.getPurpose());

        //update cv object
        cvService.UpdateCV(cvList);


   //     cvRepository.saveEditCV(cv.getId(), cv.getEmail(),cv.getGender(),cv.getPhone(),cv.getPurpose(),cv.getDob());
      //  return "redirect:/candidate/saveEditCV";
        return "redirect:/candidate/home";
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




    // xử lý add new Experience

    @GetMapping(value = "/addExperience/{id}")
    public String addExperience(Model model, @PathVariable long id) {
        ExperienceCV exp = new ExperienceCV();
        exp.setCv(cvService.findById(id));
        model.addAttribute("exp",exp);
        return "candidate/addExperience";
    }

    //save experience
    @PostMapping("/saveExperience")
    public String saveExperience(@ModelAttribute("exp") ExperienceCV exp){
//        cvService.SaveExperienceCV(exp.getCompanyName(),exp.getDescription(),exp.getEndTime(),
//                 exp.getJobTitle(), exp.getStartTime(),exp.getCv().getId());
        cvService.saveExperience(exp);
        return "redirect:/candidate/detailOneCV/"+exp.getCv().getId()+"";
    }

    //add new Education
    @GetMapping(value = "/addEducation/{id}")
    public String addEducation(Model model, @PathVariable long id) {
        EducateCV edu = new EducateCV();
        edu.setCv(cvService.findById(id));
        model.addAttribute("edu",edu);
        return "candidate/addEducation";
    }

    //save new education
    @PostMapping("/saveEducation")
    public String saveEducation(@ModelAttribute("edu") EducateCV edu){
        cvService.saveEducation(edu);
        return "redirect:/candidate/detailOneCV/"+edu.getCv().getId()+"";
    }

    //add new certificate
    @GetMapping(value = "/addCertificate/{id}")
    public String addCertificate(Model model, @PathVariable long id) {
        CertificateCV cer = new CertificateCV();
        cer.setCv(cvService.findById(id));
        model.addAttribute("cer",cer);
        return "candidate/addCertificate";
    }

    //save certificate
    @PostMapping("/saveCertificate")
    public String saveCertificate(@ModelAttribute("cer") CertificateCV cer){
        cvService.saveCertificate(cer);
        return "redirect:/candidate/detailOneCV/"+cer.getCv().getId()+"";
    }



    //add new SKillCV
    @GetMapping(value = "/addSkill/{id}")
    public String addSkillCV(Model model, @PathVariable long id) {
        SkillCV skiCV = new SkillCV();
        skiCV.setCv(cvService.findById(id));
        model.addAttribute("skiCV", skiCV);
        return "candidate/addSkill";
    }

    //save skillCV
    @PostMapping("/saveSkillCV")
    public String saveSkillCV(@ModelAttribute("ski") SkillCV skiCV){
        cvService.saveSkillCV(skiCV);
        return "redirect:/candidate/detailOneCV/"+skiCV.getCv().getId()+"";
    }



}
