package com.fu.swp391.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fu.swp391.controller.restController.dto.BenefitDTO;
import com.fu.swp391.controller.restController.dto.SkillDTO;
import com.fu.swp391.controller.restController.dto.ruleDTO;
import com.fu.swp391.entities.*;
import com.fu.swp391.repository.CompanyMajorRepository;
import com.fu.swp391.repository.CompanyRepository;
import com.fu.swp391.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("company/major")
public class MajorController {

        @Autowired
        MajorService majorService ;

        @Autowired
        SkillService skillService;

        @Autowired
        CompanyService companyService;

        @Autowired
        CompanyMajorService companyMajorService;

        @Autowired
        CompanyMajorRepository companyMajorRepository;

        @GetMapping("add")
        public String add(Model model){
            List<Major> majors = majorService.findAll();

            model.addAttribute("company_major", new CompanyMajor());
            model.addAttribute("majors", majors);

            Skill skill = skillService.findById(1);

            return "major/AddMajor";
        }
    @PostMapping("addmajor")
    public String addMajor(CompanyMajor companyMajor){


        List<Company> list = companyService.findAllCompany();
        companyMajor.setCompany(list.get(0));

//        List<MajorRule> majorRule = new ArrayList<>();
//        List<MajorBenefit> majorBenefit = new ArrayList<>();
//        List<MajorSkill> majorSkill = new ArrayList<>();

        for (ruleDTO rule:rules) {
            MajorRule r = new MajorRule();
            r.setTitle(rule.getTitle());
            r.setContent(rule.getContent());
            r.setCompanyMajor(companyMajor);
            companyMajor.getMajorRules().add(r);
        }

        for (BenefitDTO ben:bens) {
            MajorBenefit b = new MajorBenefit();
            b.setBenefit(ben.getTitle());
            b.setTitle(ben.getTitle());
            b.setCompanyMajor(companyMajor);
            companyMajor.getMajorBenefits().add(b);

        }
        for (SkillDTO skill:skills) {
            MajorSkill s = new MajorSkill();
            s.setName(skill.getName());
            s.setCompanyMajor(companyMajor);
            companyMajor.getMajorSkills().add(s);

        }
//        companyMajor.setMajorRules(majorRule);
//        for (MajorRule m: companyMajor.getMajorRules() ) {
//            System.out.println(m.getContent());
//
//        }
//        companyMajor.setMajorBenefits(majorBenefit);
//        companyMajor.setMajorSkills(majorSkill);
        companyMajorRepository.save(companyMajor);
        return "redirect:/company/major/add";
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
    public @ResponseBody String addBenefit(HttpServletRequest request) {
        String benefit = request.getParameter("benefit");
        String title = request.getParameter("title");
        Long count = Long.parseLong(request.getParameter("count"));

        BenefitDTO ben = new BenefitDTO(count,title,benefit);
        bens.add(ben);

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
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
        Gson gson = new Gson();
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

}
