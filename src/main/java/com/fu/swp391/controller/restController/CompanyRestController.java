package com.fu.swp391.controller.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.binding.entiity.Email;
import com.fu.swp391.common.enumConstants.StatusEnum;
import com.fu.swp391.config.entity.ApiError;
import com.fu.swp391.controller.restController.dto.SendRequest;
import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Request;
import com.fu.swp391.entities.User;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.RequestRepository;
import com.fu.swp391.repository.UserRepository;
import com.fu.swp391.service.CvService;
import com.fu.swp391.service.EmailSenderService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyRestController {

  @Autowired
  HelperUntil<Request> helperUntil;

  @Autowired
  EmailSenderService emailSenderServiceImpl;

  @Autowired
  RequestRepository requestRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CvService cvService;

  @PostMapping(value = "/sendRequest")
  @ResponseBody
  public ResponseEntity<Object> sendRequestToCandidate(
      @Validated @RequestBody SendRequest sendRequest,
      BindingResult bindingResult
  ) {
    System.out.println(helperUntil.getPrincipal());
//    Optional<User> user = userRepository.findUserByEmail("admin123@gmail.com");
    Optional<User> user = userRepository.findUserByEmail(helperUntil.getPrincipal());

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode objectNode = objectMapper.createObjectNode();

    if (!user.isPresent()) {
//      throw new Exception("You did not login");
      helperUntil.putKeyValue(objectNode, "internal_error", "You did not login");
      return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, "error", objectNode),
          HttpStatus.BAD_REQUEST);
    }

    if (bindingResult.hasErrors()) {
      System.out.println("Error occurred,Starting validate\n");

      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        helperUntil.putKeyValue(objectNode, fieldError.getField(),
            fieldError.getDefaultMessage());
        System.out.println("Field error " + fieldError.getField() + "\n"
            + "Message: " + fieldError.getDefaultMessage());
      }

      ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "error", objectNode);
      return new ResponseEntity<Object>(
          apiError, apiError.getStatus()
      );
    }
    //Entry here mean no error
    System.out.println("send request according to cv id" + sendRequest.getCvId() + "\n"
        + "Send to user id: " + sendRequest.getSendToUserId());
    Optional<CV> cv = cvService.getCVBySpecificId(sendRequest.getCvId());

    //check if cv is present
    if (!cv.isPresent()) {
      helperUntil.putKeyValue(objectNode, "internal_error", "CV not found");
      return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, "error", objectNode),
          HttpStatus.BAD_REQUEST);
    }

    //check if toUser is present
    Long toCandidateUserId = sendRequest.getSendToUserId();
    Optional<User> userTo = userRepository.findById(toCandidateUserId);
    if (!userTo.isPresent()) {
      helperUntil.putKeyValue(objectNode, "internal_error", "To User not found");
      return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, "error", objectNode),
          HttpStatus.BAD_REQUEST);
    }

    Long maxId = requestRepository.findMaxId();
    if(maxId==null)
      maxId = 0L;

    Request request = new Request();
    request.setSubject(sendRequest.getSubject());
    request.setFromUser(user.get());
    request.setToUser(userTo.get());
    request.setContent(sendRequest.getContent());
    request.setCv(cv.get());
    request.setStatus(StatusEnum.SENT);
    request.setId(maxId+1);

    Email emailToSend = new Email();
    emailToSend.setSubject(sendRequest.getSubject());
    emailToSend.setFrom(user.get().getEmail());
    emailToSend.setTo(cv.get().getEmail());
    emailToSend.setTemplate("/emailTemplates/sendToCandidate");
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("candidateName",cv.get().getCandidate().getName());
    model.put("companyName",user.get().getCompanies().get(0).getName());
    model.put("requestId",request.getId());
    System.out.println(cv.get().getCandidate().getName()+" candidate name");
    System.out.println(user.get().getCompanies().get(0).getName()+" company name");

    emailToSend.setProperties(model);
    System.out.println("entryy 999");
    try {
      emailSenderServiceImpl.sendHtmlMessage(emailToSend);
      requestRepository.save(request);
    } catch (Exception e) {
      e.printStackTrace();
      helperUntil.putKeyValue(objectNode, "internal_error", "Request send failed");
      return new ResponseEntity<Object>(new ApiError(HttpStatus.BAD_REQUEST, "error", objectNode),
          HttpStatus.BAD_REQUEST);
    }
    helperUntil.putKeyValue(objectNode, "message", "success");
    return new ResponseEntity<>(objectNode, HttpStatus.OK);
  }

}
