package com.fu.swp391.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.binding.entiity.PagingParam;
import com.fu.swp391.binding.entiity.SortParam;
import com.fu.swp391.common.enumConstants.PagingParameter;
import com.fu.swp391.common.enumConstants.SortEnum;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.Role;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.RoleService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

  @GetMapping("/company-list")
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
      @RequestParam(value = "orderBy", required = false) String sortBy,
      @RequestParam(value = "orderType", required = false) String orderType,

      Model model) {
    ArrayList<Candidate> candidates = null;
    int pageIndex = page != null ? page : 1;
    int sizeDef = size != null ? size : PagingParameter.PAGE_SIZE_COMPANY_ADMIN;

    //if no sortBy provide findAll no sort
    //default is ascending
    candidates = sortBy==null || sortBy.isEmpty() ? candidateService.findAllCandidates()
        : orderType == null ? candidateService.findAllCandidatesSortBy(sortBy,
            SortEnum.DESCENDING) : candidateService.findAllCandidatesSortBy(sortBy, orderType);
    List<String> fieldSort = Arrays.asList("name","age");
    SortParam sortParam = new SortParam();
    if(sortBy!=null)
    if(!sortBy.isEmpty()){
      sortParam = orderType==null?new SortParam(orderType,SortEnum.DESCENDING): new SortParam(orderType,sortBy);
    }
    //phai de o day vi sortParam se tai khoi tao
    sortParam.setOrderFields(fieldSort);

    PagingParam pageParam = new PagingParam(
        candidateService.getHelperUntilCandidate().getTotalSize(candidates.size(), sizeDef),
        candidates.size(), sizeDef, pageIndex);
    model.addAttribute("pageParam", pageParam);
    ArrayList<Candidate> getCandidatesByPaging = candidateService.getAllCandidateByPaging(
        candidates, pageIndex, sizeDef);
    pageParam.setTotalElementInCurrentPage(getCandidatesByPaging.size());
    model.addAttribute(
        "candidates", getCandidatesByPaging);
    model.addAttribute("sortParam", sortParam);
    return "/admin/candidatesList";
  }

  @RequestMapping(value = "/candidateFilter", method = RequestMethod.GET)
  public String searchCandidate(
      @RequestParam(value = "searchBy", required = false) String searchBy,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      Model model) {
    ArrayList<Candidate> candidates = candidateService.findAllCandidates();
    int pageIndex = page != null ? page : 1;
    //if no sortBy provide findAll no sort
    //default is ascending
    candidates =  candidateService.findAllCandidatesByFilter(searchBy);
    int sizeDef = size != null ? size : candidates.size();
    PagingParam pageParam = new PagingParam(
        candidateService.getHelperUntilCandidate().getTotalSize(candidates.size(), sizeDef),
        candidates.size(), sizeDef, pageIndex);
    model.addAttribute("pageParam", pageParam);
    ArrayList<Candidate> getCandidatesByPaging = candidateService.getAllCandidateByPaging(
        candidates, pageIndex, sizeDef);
    pageParam.setTotalElementInCurrentPage(getCandidatesByPaging.size());
    model.addAttribute(
        "candidates", getCandidatesByPaging);
    return "/admin/candidatesList";
  }

  @GetMapping("/searchByAddress/{id}")
  public String searchByAddress(Model model, @PathVariable long id){
    Optional<Company> optional = companyService.findbyId(id);
    model.addAttribute("ListCompanyByAddress",optional);
    return "company/ListAllCompany3";
  }

  @GetMapping("/company")
  public String renderCompany(
      @RequestParam(value = "page", required = false) Integer page, Model model) {
    ArrayList<Company> companyList = (ArrayList<Company>) companyService.findAllCompany();
    int pageIndex = 1;
//    if (page != null) {
//      pageIndex = page;
//    }
//    companyList =
//        companyService.getAllCompanyByPaging(
//            companyList, pageIndex, PagingParameter.PAGE_SIZE_COMPANY_ADMIN);
//
//    System.out.println(page);
    model.addAttribute("companies", companyList);
    return "company/ListAllCompany";
  }
  @GetMapping ("/loadCompanyToEdit/{id}")
  public String loadCompanyToEdit(Model model, @PathVariable long id) {
    Optional<Company> optionalCompany = companyService.findbyId(id);
    model.addAttribute("optionalCompany",optionalCompany.get());
    return "company/ListAllCompany3";
  }
  @PostMapping("/editCompanyToEdit/{id}")
  public String editCompanyToEdit(@Validated @ModelAttribute ("optionalCompany") Company company, BindingResult result){
    Optional<Company> optional = companyService.save(company);
    System.out.println("Name"+company.getName());
    if (result.hasErrors()){
      System.out.println("Error!!!");
    }else{

    }
    return  "redirect:/admin/company";
  }
}
