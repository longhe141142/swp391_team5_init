package com.fu.swp391.config;

import com.fu.swp391.common.enumConstants.roleEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  protected void handle(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    System.out.println("Starting hanling");
    String targetUrl = determineTargetUrl(authentication);

    //IMPORTANT
    if (response.isCommitted()) {
      System.out.println("Can't redirect");
      return;
    }

    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  /*
   * This method extracts the roles of currently logged-in user and returns
   * appropriate URL according to his/her role.
   */
  protected String determineTargetUrl(Authentication authentication) {
    String url = "";

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    List<String> roles = new ArrayList<String>();

    for (GrantedAuthority a : authorities) {
      roles.add(a.getAuthority());
    }

    if (isCandidate(roles)) {
      url = "/candidate/home";
    } else if (isAdmin(roles)) {
      url = "/candidate/home";
    } else if (isAdmin(roles)) {
<<<<<<< HEAD
      url = "/candidate/home";
=======
      url = "/admin/home";
>>>>>>> 3407486 (Long add company)
    } else {
      url = "/accessDenied";
    }
    return url;
  }

  private boolean isCandidate(List<String> roles) {
    return roles.contains(roleEnum.CANDIDATE);
  }

    private boolean isAdmin(List<String> roles) {
        return roles.contains(roleEnum.ADMIN);
    }

//  private boolean isAdmin(List<String> roles) {
//    if (roles.contains("ROLE_ADMIN")) {
//      return true;
//    }
//    return false;
//  }

  public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
    this.redirectStrategy = redirectStrategy;
  }

  protected RedirectStrategy getRedirectStrategy() {
    return redirectStrategy;
  }
}
