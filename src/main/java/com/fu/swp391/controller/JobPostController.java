package com.fu.swp391.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fu.swp391.controller.restController.dto.BenefitDTO;
import com.fu.swp391.controller.restController.dto.SkillDTO;
import com.fu.swp391.controller.restController.dto.ruleDTO;
import com.fu.swp391.entities.*;
import com.fu.swp391.repository.JobPostRepository;
import com.fu.swp391.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("company/major")
public class JobPostController {

    @Autowired
    MajorService majorService ;

    @Autowired
    SkillService skillService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyMajorService companyMajorService;

    @Autowired
    JobPostRepository jobPostRepository;



    @GetMapping("add")
    public String add(Model model){
        List<Major> majors = majorService.findAll();

        model.addAttribute("job_post", new JobPost());
        model.addAttribute("majors", majors);

        Skill skill = skillService.findById(1);

        return "major/AddMajor";
    }
    @PostMapping("addjob")
    public String addjob(JobPost jobPost){


        Company company = companyService.findCompanyByEmail(getPrincipal());
        jobPost.setCompany(company);

//        List<MajorRule> majorRule = new ArrayList<>();
//        List<MajorBenefit> majorBenefit = new ArrayList<>();
//        List<MajorSkill> majorSkill = new ArrayList<>();

        for (ruleDTO rule:rules) {
            JobRule r = new JobRule();
            r.setTitle(rule.getTitle());
            r.setContent(rule.getContent());
            r.setJobPost(jobPost);
            jobPost.getJobRules().add(r);
        }

        for (BenefitDTO ben:bens) {
            JobBenefit b = new JobBenefit();
            b.setBenefit(ben.getBenefit());
            b.setTitle(ben.getTitle());
            b.setJobPost(jobPost);
            jobPost.getJobBenefits().add(b);

        }
        for (SkillDTO skill:skills) {
            JobSkill s = new JobSkill();
            s.setName(skill.getName());
            s.setJobPost(jobPost);
            jobPost.getJobSkills().add(s);

        }
        rules = new ArrayList<>();
        bens = new ArrayList<>();
        skills = new ArrayList<>();
//        companyMajor.setMajorRules(majorRule);
//        for (MajorRule m: companyMajor.getMajorRules() ) {
//            System.out.println(m.getContent());
//
//        }
//        companyMajor.setMajorBenefits(majorBenefit);
//        companyMajor.setMajorSkills(majorSkill);
        jobPostRepository.save(jobPost);
        return "redirect:/company/listjob";
    }
    ArrayList<ruleDTO> rules = new ArrayList<>();
    ArrayList<BenefitDTO> bens = new ArrayList<>();
    @RequestMapping(value = "/addRule", method = RequestMethod.GET)
    public @ResponseBody String addRule(HttpServletRequest request) {
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        Long count = Long.parseLong(request.getParameter("count"));

        ruleDTO rule = new ruleDTO(count,title,content);
        rules.add(rule);

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(rule);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }
    @RequestMapping(value = "/addBenefit", method = RequestMethod.GET)
    public @ResponseBody String addBenefit(HttpServletRequest request){
            String benefit = request.getParameter("benefit");
            String title = request.getParameter("title");
            long id = Long.parseLong(request.getParameter("count") );

            BenefitDTO ben = new BenefitDTO(id,title,benefit);
            bens.add(ben);
            ObjectMapper mapper = new ObjectMapper();
            String ajaxResponse = "" ;
            try {
                ajaxResponse = mapper.writeValueAsString(ben);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return ajaxResponse;

    }


    @RequestMapping(value = "/deleteRule", method = RequestMethod.GET)
    public @ResponseBody String deleteRule(HttpServletRequest request) {

        Long id = Long.parseLong(request.getParameter("id"));

        ruleDTO remove = new ruleDTO();
        for (ruleDTO rule:rules) {
            if(rule.getId().equals(id)){
                remove = rule;
            }
        };
        rules.remove(remove);
        if(rules.size()>0){
            long i = 0;
            for (ruleDTO rule:rules) {
                i ++;
                rule.setId(i);
            };
        }

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString("");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @RequestMapping(value = "/deleteBenefit", method = RequestMethod.GET)
    public @ResponseBody String deleteBenefit(HttpServletRequest request) {

        Long id = Long.parseLong(request.getParameter("id"));

        BenefitDTO remove = new BenefitDTO();
        for (BenefitDTO ben :bens) {
            if(ben.getId().equals(id)){
                remove = ben;
            }
        };
        bens.remove(remove);
        if(bens.size()>0){
            long i = 0;
            for (BenefitDTO ben :bens) {
                i ++;
                ben.setId(i);
            };
        }

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString("");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }
    ArrayList<SkillDTO> skills = new ArrayList<>();
    @RequestMapping(value = "/addSkill",  method = RequestMethod.GET)
    public  @ResponseBody String addSkill(HttpServletRequest request)
    {
        String skillName = request.getParameter("name");
        SkillDTO skill = new SkillDTO(skillName);
        skills.add(skill);
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(skill);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }
    @RequestMapping(value = "/deleteSkill",  method = RequestMethod.GET)
    public  @ResponseBody String deleteSkill(HttpServletRequest request)
    {
        String skillName = request.getParameter("name");
        SkillDTO s = new SkillDTO();
        for (SkillDTO skill: skills) {
            if(skill.getName().equals(skillName)){
                s = skill;
            }

        }
        skills.remove(s);
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }
    @RequestMapping(value = "/getSkill", method = RequestMethod.GET)
    public @ResponseBody String  getSkill(HttpServletRequest request) {

        Long id = Long.parseLong(request.getParameter("id"));

        Set<Skill> set = majorService.findById(id).get().getSkills();
        List<SkillDTO> list = new ArrayList<>();
        for (Skill e : set) {
            SkillDTO s = new SkillDTO(e.getId(),e.getName(),e.getTitle());
            list.add(s);
        }

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;


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
