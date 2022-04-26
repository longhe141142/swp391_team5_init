package com.fu.swp391.controller;

import com.fu.swp391.entities.User;
import com.fu.swp391.repository.UserRepository;
import com.fu.swp391.service.UserService;
import com.fu.swp391.service.UserServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangePasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl2 userServiceImpl2;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("user", userService.findByEmail(this.getPrincipal()));
        return "admin/change-password";
    }

    @PostMapping("/saveChangePassword")
    public String saveChangePassword(HttpServletRequest request, Model model) {
       String oldPassword = request.getParameter("oldPass");
       String newPassword = request.getParameter("newPass");
       String confirmPassword = request.getParameter("confirmPass");

        User user = userService.findByEmail(getPrincipal());

        if (user.getPasswordEncoder().equals(oldPassword)) {
            if (newPassword.equals(confirmPassword)) {
                user.setPasswordEncoder(newPassword);
              userServiceImpl2.updatePassword(user, newPassword);
                return "redirect:/admin/list-company";
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
