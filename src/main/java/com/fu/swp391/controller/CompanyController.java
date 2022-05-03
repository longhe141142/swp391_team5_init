package com.fu.swp391.controller;

import com.fu.swp391.binding.entiity.Email;
import com.fu.swp391.binding.entiity.PagingParam;
import com.fu.swp391.binding.entiity.exception.CandidateNotFound;
import com.fu.swp391.common.enumConstants.PagingParameter;
import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.JobPost;
import com.fu.swp391.entities.User;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.JobPostRepository;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyMajorService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.CvService;
import com.fu.swp391.service.JobPostService;
import com.fu.swp391.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    HelperUntil<CV> cvHelperUntil;

    @Autowired
    UserService userService;

    @Autowired
    CvService cvService;

    public CompanyController(CompanyService companyService,
        CompanyMajorService companyMajorService) {
        this.companyService = companyService;
        this.companyMajorService = companyMajorService;
    }

    @GetMapping("/listjob")
    public String listAllJob(Model model) {
        model.addAttribute("Jobs", jobPostRepository.findJobPostByCompanyId(1));

        Company company = companyService.findCompanyByEmail(getPrincipal());
        model.addAttribute("Jobs", jobPostRepository.findJobPostByCompanyId(company.getId()));

        return "/company/ListAllJob";
    }

    @GetMapping("/jobpost/view/{id}")
    public String jobDetail(Model model, @PathVariable("id") long id) {

        model.addAttribute("job_detail", jobPostRepository.findJobPostById(id));
        return "/company/JobDetail";
    }

    @GetMapping("/jobpost/delete/{id}")
    public String deleteJob(Model model, @PathVariable("id") long id) {

       jobPostRepository.deleteById(id);


        return "forward:/company/listjob";
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
        model.addAttribute("candidates", getCandidatesByPaging);
        model.addAttribute("company", yourCompany);

        return "/company/company-home/home";
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

    @GetMapping("/candidate")
    public String candidateDetail(
        @RequestParam(value = "id") Long id,
        Model model
    ) throws CandidateNotFound {
        Optional<Candidate> candidate = companyService.getCandidateById(id);
        ArrayList<CV> candidatesListCastIgnorePersistentBagException = new ArrayList<>();
        if (candidate.isPresent()) {
            for (CV cv : candidate.get().getCVPublic()) {
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
        Email email = new Email();
        Optional<CV> cv = cvService.getCVBySpecificId(id);
        if (cv.isPresent()) {
            model.addAttribute("cv", cv.get());
            model.addAttribute("email",email);
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

