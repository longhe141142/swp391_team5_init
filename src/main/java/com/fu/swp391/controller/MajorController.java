package com.fu.swp391.controller;


import com.fu.swp391.entities.JobPost;
import com.fu.swp391.entities.Major;
import com.fu.swp391.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("company/major")
public class MajorController {

        @Autowired
        JobPostService jobPostService;

        @GetMapping("add")
        public String add(Model model){
            List<Major> majors = jobPostService.findAll();
            model.addAttribute("company_major", new JobPost());
            model.addAttribute("majors", majors);

            return "major/AddMajor";
        }
    @PostMapping("addmajor")
    public String addMajor(JobPost jobPost){

        System.out.println(jobPost.toString());

        return "major/AddMajor";
    }

}
