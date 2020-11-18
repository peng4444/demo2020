package cn.pbj.demo2020.sso_vue.advice;


import cn.pbj.demo2020.sso_vue.controller.AdminController;
import cn.pbj.demo2020.sso_vue.controller.HomeController;
import cn.pbj.demo2020.sso_vue.controller.UserController;
import cn.pbj.demo2020.sso_vue.domain.ErrorDetails;
import cn.pbj.demo2020.sso_vue.exception.CustomizeException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice(assignableTypes = {UserController.class, HomeController.class, AdminController.class})
public class CustomControllerAdvice {
    @ExceptionHandler
    public HttpEntity customExceptionHandler(CustomizeException e, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorDetails.setMessage(e.getLocalizedMessage());
        errorDetails.setPath(request.getServletPath());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDetails);
    }
}
