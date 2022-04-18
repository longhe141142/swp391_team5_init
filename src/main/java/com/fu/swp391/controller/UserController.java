package com.fu.swp391.controller;

import com.fu.swp391.binding.entiity.UserCandidate;
import com.fu.swp391.common.enumConstants.Gender;
import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.common.enumConstants.accountStatusEnum;
import com.fu.swp391.common.enumConstants.roleEnum;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Role;
import com.fu.swp391.entities.User;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller()
// @RequestMapping(value = "/user")
public class UserController {

  @Autowired RoleService roleService;

  @Autowired CandidateService candidateService;

  @Autowired GenderEnum genderEnum;

  @Autowired
  UserService userService;

  @GetMapping("/register")
  public String registerUser(Model model) {
    System.out.println("Entry SignUp");
    return "register/register";
  }

  @GetMapping("/registerTest")
  public String registerTest(Model model) {
    System.out.println("Entry SignUp");
    model.addAttribute("userCandidate", new UserCandidate());
    List<String> genderStringList = userService.getListGender();
    model.addAttribute("listGender",genderStringList);
    return "register/register";
  }

  @GetMapping("/testTemplate")
  public String testTemplate() {
    System.out.println("Entry template");
    return "register/register";
  }



  @PostMapping("/registerTest")
  public String registerTest(
      @Validated @ModelAttribute("userCandidate") UserCandidate userCandidate,
      BindingResult userCandidateResult,
      RedirectAttributes redirect,
      Model model)
      throws Exception {


    if (userCandidateResult.hasErrors()) {
      List<FieldError> f = userCandidateResult.getFieldErrors();
      f.forEach(
          name -> {
            System.out.println(name.getField());
            System.out.println( name.getDefaultMessage());
          });

      System.out.println("error occured");

      return "redirect:/registerTest";
    }
    Optional<Role> roleUser = userService.addRoleToUser(roleEnum.CANDIDATE,userCandidate);
    User user = userCandidate.getUser();
    //tham chieu phuong thuc
    //consumer bind
    roleUser.ifPresent(user::setRole);
    user.setToken("user");
    user.setStatus(accountStatusEnum.USER_CREATED);
    Candidate candidate = userCandidate.getCandidate();
    user.setCandidate(candidate);
    userService.save(user);
    return "login/login";
  }

  @PostMapping("/register")
  public String registerUser(
      @Validated @ModelAttribute("userCandidate") UserCandidate userCandidate,
      BindingResult userCandidateResult,
      RedirectAttributes redirect,
      Model model)
      throws Exception {
    Optional<Role> roleUser = userService.addRoleToUser(roleEnum.CANDIDATE,userCandidate);
    User user = userCandidate.getUser();
    if (roleUser.isPresent()) user.setRole(roleUser.get());
    user.setToken("user");
    user.setStatus(accountStatusEnum.USER_CREATED);
    //        user.setBirthDate(user.getBirthDate());
    if (userCandidateResult.hasErrors()) {
      if (userService.findByEmail(user.getEmail()) != null) {
        model.addAttribute("errolEmail", "Email was existed");
      }
      return "register/register";
    } else {
      // user.setPassword(passwordEncoder.encode(user.getPassword()));
      redirect.addFlashAttribute("globalMessage", "Register successfully.");
      userService.save(user);
      return "redirect:/register";
    }
  }
}
