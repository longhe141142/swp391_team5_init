package com.fu.swp391.controller;

import com.fu.swp391.binding.entiity.UserCandidate;
import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.common.enumConstants.accountStatusEnum;
import com.fu.swp391.common.enumConstants.roleEnum;
import com.fu.swp391.entities.User;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Arrays;
import java.util.List;

@Controller()
public class UserController {

  @Autowired RoleService roleService;

  @Autowired @Qualifier("candidateServiceImpl") CandidateService candidateService;

  @Autowired GenderEnum genderEnum;

  @Autowired
  PasswordEncoder encoder;



  @Autowired
  UserService userService;


  @GetMapping("/register")
  public String registerTest(Model model) {
    System.out.println("Entry SignUp");
    model.addAttribute("userCandidate", new UserCandidate());
    List<String> genderStringList = userService.getListGender();
    model.addAttribute("listGender",genderStringList);
    return "register/register";
  }



  @PostMapping("/registerTest")
  public String registerTest(
      @Validated @ModelAttribute("userCandidate") UserCandidate userCandidate,
      BindingResult userCandidateResult,
      RedirectAttributes redirect,
      Model model)
      throws Exception {
    try {
      System.out.println(userCandidate.getUser().getPassword() + "9999[password]");
      if (userCandidateResult.hasErrors()) {
        List<FieldError> f = userCandidateResult.getFieldErrors();
        f.forEach(
            name -> {
              System.out.println(name.getField());
              System.out.println(name.getDefaultMessage());
            });
        System.out.println("error occured");
        //add atrribute
        return "redirect:/register";
      }

      String[] roleArray = new String[] {roleEnum.USER, roleEnum.CANDIDATE};
      List<String> roleList = new ArrayList<>(Arrays.asList(roleArray));
      User user = userService.addRoleToUser(roleList, userCandidate);
      user.setToken("user");
      user.setStatus(accountStatusEnum.USER_CREATED);
      user.getRoles()
          .forEach(
              role -> {
                System.out.println("ROLE::NAME[" + role.getName() + "]");
              });
      System.out.println(user.getCandidates().get(0).getName() + "CANDIDATE::NAME");
      System.out.println(user.getCandidates().get(0).getGender() + "CANDIDATE::GENDER");
      user.setPasswordEncoder(encoder.encode( user.getPassword()));
      userService.save(user);
      return "login/login";
    } catch (Exception e) {
      System.out.println(e.getStackTrace());
      throw e;
      }
    }
//    @RequestMapping(value = "/templates/homeAdmin.html/", method = RequestMethod.GET){
//
//    }



  //  @PostMapping("/register")
  //  public String registerUser(
  //      @Validated @ModelAttribute("userCandidate") UserCandidate userCandidate,
  //      BindingResult userCandidateResult,
  //      RedirectAttributes redirect,
  //      Model model)
  //      throws Exception {
  //    Optional<Role> roleUser = userService.addRoleToUser(roleEnum.CANDIDATE,userCandidate);
  //    User user = userCandidate.getUser();
  //    System.out.println(roleUser.get().getName()+ "[Role Name]");
  //    if (roleUser.isPresent()) user.setRole(roleUser.get());
  //    user.setToken("user");
  //    user.setStatus(accountStatusEnum.USER_CREATED);
  //    //        user.setBirthDate(user.getBirthDate());
  //    if (userCandidateResult.hasErrors()) {
  //      if (userService.findByEmail(user.getEmail()) != null) {
  //        model.addAttribute("errolEmail", "Email was existed");
  //      }
  //      return "register/register";
  //    } else {
  //      // user.setPassword(passwordEncoder.encode(user.getPassword()));
  //      redirect.addFlashAttribute("globalMessage", "Register successfully.");
  //      userService.save(user);
  //      return "redirect:/register";
  //    }
  //  }

  @GetMapping("/signIn")
  public String login(Model model) {
    return "login/login";
  }



//    @PostMapping("/register")
//    public String registerUser(@Validated @ModelAttribute("user") User user , BindingResult result , RedirectAttributes redirect, Model model) throws Exception {
//
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
//        }
//    }

}
