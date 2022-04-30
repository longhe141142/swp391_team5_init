package com.fu.swp391.controller;

import com.fu.swp391.binding.entiity.PagingParam;
import com.fu.swp391.binding.entiity.exception.CandidateNotFound;
import com.fu.swp391.common.enumConstants.PagingParameter;
import com.fu.swp391.entities.*;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.entities.User;
import com.fu.swp391.repository.JobPostRepository;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyMajorService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.CvService;
import com.fu.swp391.service.JobPostService;
import com.fu.swp391.service.UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fu.swp391.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyMajorService companyMajorService;

    @Autowired
    HelperUntil<Request> helperUntilCompany;
    @Autowired
    HelperUntil<CV> helperUntilCV;
    @Autowired
    CandidateService candidateService;

    @Autowired
    JobPostService jobPostService;

    @Autowired
    JobPostRepository jobPostRepository;

    @Autowired
    HelperUntil<Candidate> candidateHelperUntil;

    @Autowired
    HelperUntil<CV> cvHelperUntil;

    @Autowired
    UserService userService;

    @Autowired
    CvService cvService;




    @Autowired
    RequestService requestService;
    public CompanyController(CompanyService companyService, CompanyMajorService companyMajorService) {
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
        System.out.println("Size_of_company: "+list.size());
        return "company/ListAllCompany";
    }

    @GetMapping("/DetailCompany12/{id}")
    public String DetailCompany(Model model, @PathVariable long id) {
        List<JobPost> ListCompanyDetail = companyMajorService.findCompanyMajorsByCompanyId(id);
        model.addAttribute("ListCompanyDetail12", ListCompanyDetail);
        return "company/ListAllCompany";
    }

    @GetMapping("/ListCvRequest/{id}")
    public String ListRequest(Model model,@PathVariable int id) {
        String email = getPrincipal();
        User user = userService.findByEmail(email);
        System.out.println(email);
        ArrayList<Request> ListRequest1 = requestService.findAllByToUserId(user.getId());
        ArrayList<Request> ListRequest = helperUntilCompany.PagingElement(ListRequest1,id,5);
        List<String> jobPostList = new ArrayList<>();
        for (Request re: ListRequest
             ) {
            re.setComment(companyMajorService.FindJobById(re.getJobPost().getId()).getMajorName());
        }
        int size = ListRequest.size();
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
        model.addAttribute("ListPage",listsopage);
        model.addAttribute("listJob",jobPostList);
        model.addAttribute("ListRequest",ListRequest);
        Optional<Company> company =  companyService.findbyId(user.getId());
        model.addAttribute("compamny",company);
        return "company/ListRequest";
    }

    @GetMapping("/listcandidateCV/{id}")
    public String ListCvcandidate(Model model,@PathVariable int id) {

        List<CV> listallcv = cvService.getAllCV();
        ArrayList<CV> ListRequest = helperUntilCV.PagingElement((ArrayList<CV>) listallcv ,id,5);
        List<String> jobPostList = new ArrayList<>();
        int size = ListRequest.size();
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
        model.addAttribute("ListPage",listsopage);
        model.addAttribute("listJob",jobPostList);
        model.addAttribute("ListRequest",ListRequest);
        return "company/listcandidateCV";
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
        model.addAttribute("candidates", getCandidatesByPaging);
        model.addAttribute("company", yourCompany);

        return "/company/company-home/home";
    }


    @GetMapping("/candidate")
    public String candidateDetail(
        @RequestParam(value = "id") Long id,
        Model model
    ) throws CandidateNotFound {
        Optional<Candidate> candidate = companyService.getCandidateById(id);
        ArrayList<CV> candidatesListCastIgnorePersistentBagException = new ArrayList<>();
        if (candidate.isPresent()) {
            for (CV cv : candidate.get().getCv()) {
                System.out.println(cv.getId());
                candidatesListCastIgnorePersistentBagException.add(cv);
            }
            PagingParam pagingParam = new PagingParam(
                PagingParameter.PAGE_SIZE_COMPANY_CANDIDATE_DETAIL_CV);
            candidatesListCastIgnorePersistentBagException = cvHelperUntil.PagingElement(
                candidatesListCastIgnorePersistentBagException, 1,
                PagingParameter.PAGE_SIZE_COMPANY_CANDIDATE_DETAIL_CV);
            model.addAttribute("pagingParam", pagingParam);
            model.addAttribute("cvListOfCandidate", candidatesListCastIgnorePersistentBagException);
            model.addAttribute("candidate", candidate.get());
        } else {
            throw new CandidateNotFound(id);
        }
        return "/company/company-candidate/candidate-detail";
    }

    @GetMapping("/candidate/cv")
    public String candidateCvDetail(
        @RequestParam(value = "id") Long id,
        Model model
    ) throws Exception {
        Optional<CV> cv = cvService.getCVBySpecificId(id);
        if (cv.isPresent()) {
            model.addAttribute("cv", cv.get());
            return "/company/company-candidate/cv-detail/cv-detail";
        } else {
            throw new Exception("CV doesn't exist");
        }
    }


    @GetMapping("/candidate/cv/seeMore")
    public String seeMoreCV(
        //id value candidateId
        @RequestParam(value = "id") Long id,
        @RequestParam(value = "page") Integer page,
        Model model
    ) throws CandidateNotFound {
        Optional<Candidate> candidate = candidateService.getCandidateById(id);
        //validate @RequestParam
        int pageIndex = page == null ? 1 : page;
        if (candidate.isPresent()) {

            //get list of cv which is PUBLIC
            List<CV> cvsPublic = candidate.get().getCVPublic();
            //Get total page
            int totalPage = cvHelperUntil.getTotalSize(cvsPublic.size(),
                PagingParameter.PAGE_SIZE_COMPANY_CANDIDATE_DETAIL_CV);
            //Ignore bagException
            ArrayList<CV> cvsPublicConvertToArrayList = new ArrayList<>(cvsPublic);
            //paging by helper.util, get arraylist in one page
            cvsPublicConvertToArrayList = cvHelperUntil.PagingElement(cvsPublicConvertToArrayList,
                pageIndex, PagingParameter.PAGE_SIZE_COMPANY_CANDIDATE_DETAIL_CV);
            //get PagingParam
            PagingParam pagingParam = new PagingParam(totalPage, cvsPublicConvertToArrayList.size(),
                PagingParameter.PAGE_SIZE_COMPANY_CANDIDATE_DETAIL_CV, pageIndex);

            model.addAttribute("listCvsPublic",cvsPublicConvertToArrayList);
            model.addAttribute("pagingParam",pagingParam);
            model.addAttribute("candidate",cvsPublicConvertToArrayList.get(0).getCandidate());
            return "/company/company-candidate/cv-list/cvList";
        } else {
            throw new CandidateNotFound(id);
        }
    }

}

