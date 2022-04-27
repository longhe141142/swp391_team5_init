package com.fu.swp391.controller;

import com.fu.swp391.entities.User;
import com.fu.swp391.repository.UserRepository;
import com.fu.swp391.service.UserService;
import com.fu.swp391.service.UserServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangePasswordController {
//    @Autowired
//    PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl2 userServiceImpl2;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        System.out.println("user"+getPrincipal());
        //model.addAttribute("user", userService.findByEmail(this.getPrincipal()));
        return "admin/change-password";
    }

    @PostMapping("/saveChangePassword")
    public String saveChangePassword(HttpServletRequest request, Model model) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String oldPassword = request.getParameter("oldPass");
       String newPassword = request.getParameter("newPass");
       String confirmPassword = request.getParameter("confirmPass");
        System.out.println(getPrincipal());
        System.out.println("Duc:  Oldpass: "+ oldPassword+"\n" +
                "new pass: "+newPassword+"\n" +
                "Confirm pass: "+confirmPassword);

        User user = userService.findByEmail(getPrincipal());
        System.out.println("passsss"+user.getPasswordEncoder());
        System.out.println("passsss     "+encoder.encode(oldPassword));
        System.out.println("new"+newPassword +"and"+confirmPassword);

//        if(encoder.matches(oldPassword,user.getPasswordEncoder())){
//            System.out.println("Matched");
//        }
        if (encoder.matches(oldPassword,user.getPasswordEncoder())) {
            System.out.println("matched ");

            if (newPassword.equals(confirmPassword)) {
                System.out.println("matched part 2");
                user.setPasswordEncoder(newPassword);
              userServiceImpl2.updatePassword(user, newPassword);
                return "redirect:/auth/logout";
            }
        }
        return "redirect:/changePassword";
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
