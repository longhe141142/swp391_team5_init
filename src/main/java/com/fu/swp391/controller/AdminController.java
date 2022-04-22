package com.fu.swp391.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.config.entity.ApiError;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.Role;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RoleService roleService;

  @Autowired CompanyService companyService;

    @GetMapping("/")
    public String renderAdminHome(){
        return "admin/homeAdmin";
    }

    //admin
    @GetMapping("/home")
    public String homeAdmin(){
        return "/admin/homeAdmin";
    }
    @GetMapping("/login")
    public String loginAdmin(){
        return "/admin/login";
    }
    @GetMapping("/register")
    public String registerAdmin(){
        return "/admin/register";
    }
    @GetMapping("/forgotPassword")
    public String forgetPasswordAdmin(){
        return "/admin/forgot-password";
    }



    @GetMapping("/company")
    public String renderCompanyManagement(@RequestParam(value = "page", required = false) Long page){
//        model.addAttribute("employees", this.employeeService.getEmployees(page));
//        return "listing";

        return "company/ListAllCompany";
    }

  //  @RequestMapping(value = "/addCompany", method = RequestMethod.POST, produces =
  // "application/json")


  // example api
  @RequestMapping(value = "/example", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> getObj() {
    List<ObjectNode> entities = new ArrayList<ObjectNode>();
    try {
      List<Role> roles = roleService.getAllRole();

      for (Role r : roles) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("name", r.getName());
        json.put("description", r.getDescription());
        json.put("id", r.getId());
        entities.add(json);
      }
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println(e.getMessage());
    }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

}
