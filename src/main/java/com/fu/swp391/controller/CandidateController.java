package com.fu.swp391.controller;

import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller()
public class CandidateController {
    @Autowired
    RoleService roleService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    GenderEnum genderEnum;

    @Autowired
    UserService userService;




}
