package com.fu.swp391.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.common.enumConstants.PagingParameter;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.Role;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RoleService roleService;
  @Autowired CandidateService candidateService;

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
  public String renderCompanyManagement(
      @RequestParam(value = "page", required = false) Integer page, Model model) {
    ArrayList<Company> companyList = (ArrayList<Company>) companyService.findAllCompany();
    int pageIndex = 1;
    if (page != null) {
      pageIndex = page;
    }
    companyList =
        companyService.getAllCompanyByPaging(
            companyList, pageIndex, PagingParameter.PAGE_SIZE_COMPANY_ADMIN);

    System.out.println(page);
    model.addAttribute("companies", companyList);
    return "company/ListAllCompany2";
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

  @RequestMapping(value = "/candidate", method = RequestMethod.GET)
  public String getCandidates(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      Model model) {
    int pageIndex = page != null ? page : 1;
    int sizeDef = size != null ? size : PagingParameter.PAGE_SIZE_COMPANY_ADMIN;
    ArrayList<Candidate> candidates = candidateService.findAllCandidates();
    model.addAttribute(
        "candidates", candidateService.getAllCandidateByPaging(candidates, pageIndex, sizeDef));
    return "/admin/candidatesList";
  }
}
