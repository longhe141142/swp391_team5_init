package com.fu.swp391.controller.ErrorController;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
  @RequestMapping("/error")
  public String handleError() {
    //do something like logging
    return "/404Page/404";
  }
}
