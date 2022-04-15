package com.fu.swp391.controller;

import com.fu.swp391.common.enumConstants.accountStatusEnum;
import com.fu.swp391.common.enumConstants.roleEnum;
import com.fu.swp391.entities.Role;
import com.fu.swp391.entities.User;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String registerUser(@Validated @ModelAttribute("user") User user , BindingResult result , RedirectAttributes redirect, Model model) throws Exception {

//        Optional<Role> roleUser = roleService.findRoleByDescription(roleEnum.ADMIN);
//
//        if (roleUser.isPresent())
//         user.setRole(roleUser.get());
//
//
//        user.setToken("user");
//        user.setStatus(accountStatusEnum.USER_CREATED);
//        user.setName(user.getName());
//
//        if (result.hasErrors()) {
//            if(userService.findByEmail(user.getEmail()) != null){
//                model.addAttribute("errolUsername", "email was existed");
//            }
//            if(userService.findByEmail(user.getEmail()) != null) {
//                model.addAttribute("errolEmail", "Email was existed");
//            }
//            return "/Pages/modal-user/user-signup";
//        }
//        else if(userService.findByEmail(user.getEmail()) != null){
//            model.addAttribute("errolUsername", "Username was existed");
//            if(userService.findByEmail(user.getEmail()) != null) {
//                model.addAttribute("errolEmail", "Email was existed");
//            }
//            return "signup page";
//        }
//        else if(userService.findByEmail(user.getEmail()) != null){
//            model.addAttribute("errolEmail", "Email was existed");
//            return "signup page";
//        }
//        else {
//            //user.setPassword(passwordEncoder.encode(user.getPassword()));
//            redirect.addFlashAttribute("globalMessage", "Register successfully.");
//            userService.save(user);
//            return "redirect:/signup";
//
        return "redirect:/page-to-redirect";
        }
    }


