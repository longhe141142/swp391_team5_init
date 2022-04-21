package com.fu.swp391.controller.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.config.entity.ApiError;
import com.fu.swp391.controller.restController.dto.addCompany;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.User;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("admin")
public class AdminRestController {
  @Autowired Validator validator;
  @Autowired CompanyService companyService;
  @Autowired HelperUntil helperUntil;


  @PostMapping(
      value = "/addCompany",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  @ResponseBody
//  @CrossOrigin
  public ResponseEntity<Object> addCompany(
      @RequestPart("file") MultipartFile file,
      @Validated @RequestPart("addCompanyDto") addCompany addCompanyDto,
      BindingResult bindingresult) {
    MultipartFile multipartFile = file;
    String fileName = multipartFile.getOriginalFilename();
    System.out.println(fileName);
    ObjectNode responseBodyError = null;

    if (bindingresult.hasErrors()) {
      System.out.println("entry");
      ObjectMapper mapper = new ObjectMapper();
      responseBodyError = mapper.createObjectNode();
      if (addCompanyDto.user == null || addCompanyDto.company == null) {
        if (addCompanyDto.user == null) {
          helperUntil.putKeyValue(
              responseBodyError, "userError", "Please enter email and password");
        }
        if (addCompanyDto.company == null) {
          helperUntil.putKeyValue(
              responseBodyError, "companyError", "Please input company information");
        }
      } else {
        for (FieldError err : bindingresult.getFieldErrors()) {
          helperUntil.putKeyValue(responseBodyError, err.getField(), err.getDefaultMessage());
          System.out.println(err.getField());
          System.out.println(err.getDefaultMessage());
        }
      }

      return new ResponseEntity<Object>(
          new ApiError(HttpStatus.BAD_REQUEST, "error", responseBodyError), HttpStatus.BAD_REQUEST);
    }
    System.out.println("Entry");
    System.out.println("Company name" + addCompanyDto.company.getName());
    System.out.println("Company description" + addCompanyDto.company.getDescription());
    System.out.println("user create email" + addCompanyDto.user.getEmail());

    try {
      FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Company company = addCompanyDto.company;
    User user = addCompanyDto.user;
    Company companyCreate = companyService.addCompany(company, user);
    return new ResponseEntity<Object>(companyCreate, HttpStatus.OK);
  }

  @Value("C:/Users/84866/Videos/swp391 (1)/swp391/src/main/resources/fileUpload/")
  private String fileUpload;

  @PostMapping(
      value = "/upload",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public Company upload(
      @RequestPart("company") String company, @RequestPart("file") MultipartFile file) {
    Company companyJson = companyService.getJson(company, file);
    return companyJson;
  }
}
