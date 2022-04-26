package com.fu.swp391.config;

import com.fu.swp391.entities.User;
import com.fu.swp391.helper.HelperUntil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class CustomLogoutSuccessHandler implements LogoutHandler {

  @Autowired
  HelperUntil<User> helperUntil;



  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
  }
}
