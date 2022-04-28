package com.fu.swp391.config;

import com.fu.swp391.binding.entiity.exception.PrincipalBuildException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class CustomExceptionHandler {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//
//    @org.springframework.web.bind.annotation.ExceptionHandler({ ConstraintViolationException.class })
//    public ResponseEntity<Object> handleConstraintViolation(
//            ConstraintViolationException ex, WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectNode responseBody = mapper.createObjectNode();
//        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            responseBody.put(violation.getPropertyPath().toString(),violation.getMessage());
//            errors.add(violation.getRootBeanClass().getName() + " " +
//                    violation.getPropertyPath() + ": " + violation.getMessage());
//        }
//
//        ApiError apiError =
//                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,responseBody);
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), apiError.getStatus());
//    }


    @org.springframework.web.bind.annotation.ExceptionHandler(UserBlockedException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ex.printStackTrace();
        System.out.println("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("/404Page/account-denied");
        return mav;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView handleError2(HttpServletRequest req, Exception ex) {
        ex.printStackTrace();
        System.out.println("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("/404Page/404");
        return mav;
    }

    @ExceptionHandler(PrincipalBuildException.class)
    public ModelAndView handleAuthBuildFailed(HttpServletRequest req, Exception ex){
        System.out.println(ex.getMessage());
        System.out.println("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("/404Page/account-denied");
        return mav;
    }

}