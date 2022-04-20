package com.fu.swp391.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fu.swp391.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RoleService roleService;

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


    @RequestMapping(value = "/example", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getObj(){
        List<JSONPObject> entities = new ArrayList<JSONPObject>();
//        try {
////            HocsinhModel hsm = new HocsinhModel();
////            for (Hocsinh hs: hsm.findAll()){
////                JSONObject obj = new JSONObject();
////                obj.put("mahs", hs.getMahs());
////                obj.put("ten", hs.getTen());
////                obj.put("tuoi", hs.getTuoi());
////                entities.add(obj);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println(e.getMessage());
//        }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }


}
