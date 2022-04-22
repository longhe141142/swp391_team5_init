package com.fu.swp391.controller.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.config.entity.ApiError;
import com.fu.swp391.controller.restController.dto.addCompany;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.User;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.CompanyRepository;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.UserService;
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
import java.util.Optional;

@RestController
@RequestMapping("admin")
public class AdminRestController {
  @Autowired Validator validator;
  @Autowired CompanyService companyService;
  @Autowired HelperUntil helperUntil;
  @Autowired UserService userService;
  @Autowired CompanyRepository companyRepository;

  @PostMapping(
      value = "/upload-company-image",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  @ResponseBody
  public ResponseEntity<Object> uploadImage(
      @RequestPart("file") MultipartFile file, @RequestParam(required = false) String id) {
    ResponseEntity<Object> isSomethingWrong = validateFile(file);
    if (isSomethingWrong != null) {
      return isSomethingWrong;
    } else {
      MultipartFile multipartFile = file;
      String fileName = multipartFile.getOriginalFilename();
      try {
        FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
      } catch (IOException e) {
        e.printStackTrace();
      }
      ObjectMapper mapper = new ObjectMapper();
      ObjectNode responseBody = mapper.createObjectNode();
      if (id.isEmpty()) {
        helperUntil.putKeyValue(responseBody, "error", "Company not found");
        ApiError apiErr = new ApiError(HttpStatus.BAD_REQUEST, "Company not found", responseBody);
        return new ResponseEntity<Object>(apiErr, apiErr.getStatus());
      } else {
        Long companyId = Long.parseLong(id);
        Optional<Company> company = companyService.findbyId(companyId);
        // lambda expression
        System.out.println("company found:" + company.get().getName());
        company.ifPresent(value -> value.setCompanyImageUrl(fileName));
        companyRepository.save(company.get());
      }
      helperUntil.putKeyValue(responseBody, "message", "Upload Success");
      helperUntil.putKeyValue(responseBody, "status", HttpStatus.OK.value());
      return new ResponseEntity<Object>(responseBody, HttpStatus.OK);
    }
  }

  @PostMapping(value = "/addCompany")
  @ResponseBody
  public ResponseEntity<Object> addCompanyRequest(
      @Validated @RequestBody addCompany addCompanyDto, BindingResult bindingResult) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode responseBodyError = mapper.createObjectNode();
    if (bindingResult.hasErrors()) {
      System.out.println("entry");
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
        for (FieldError err : bindingResult.getFieldErrors()) {
          helperUntil.putKeyValue(responseBodyError, err.getField(), err.getDefaultMessage());
          System.out.println(err.getField());
          System.out.println(err.getDefaultMessage());
        }
      }
      return new ResponseEntity<Object>(
          new ApiError(HttpStatus.BAD_REQUEST, "error", responseBodyError), HttpStatus.BAD_REQUEST);
    }

    if(userService.findUserByEmail(addCompanyDto.user.getEmail()).isPresent()){
      helperUntil.putKeyValue(responseBodyError, "user_existed", "user is already existed");
      return new ResponseEntity<Object>(
              new ApiError(HttpStatus.BAD_REQUEST, "error", responseBodyError), HttpStatus.BAD_REQUEST);
    }

    System.out.println("Entry");
    System.out.println("Company name" + addCompanyDto.company.getName());
    System.out.println("Company description" + addCompanyDto.company.getDescription());
    System.out.println("user create email" + addCompanyDto.user.getEmail());

    Company company = addCompanyDto.company;
    User user = addCompanyDto.user;
    Company companyCreate = companyService.addCompany(company, user);
    return new ResponseEntity<Object>(companyCreate, HttpStatus.OK);
  }

  @Value("C:/Users/84866/Videos/swp391 (1)/swp391/src/main/resources/fileUpload/")
  private String fileUpload;

  private boolean checkFileEmpty(MultipartFile file) {
    return file.isEmpty();
  }

  private ResponseEntity<Object> validateFile(MultipartFile file) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode responseBodyError = mapper.createObjectNode();
    if (checkFileEmpty(file)) {
      helperUntil.putKeyValue(responseBodyError, "image", "Please select image");
      System.out.println("file empty");
      return new ResponseEntity<>(
          new ApiError(HttpStatus.BAD_REQUEST, "error", responseBodyError), HttpStatus.BAD_REQUEST);
    }
    return null;
  }
}