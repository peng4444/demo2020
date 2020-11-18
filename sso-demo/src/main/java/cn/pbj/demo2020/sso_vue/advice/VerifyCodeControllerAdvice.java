package cn.pbj.demo2020.sso_vue.advice;


import cn.pbj.demo2020.sso_vue.controller.VerifyCodeController;
import cn.pbj.demo2020.sso_vue.domain.ErrorDetails;
import cn.pbj.demo2020.sso_vue.exception.VerifyFailedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author puzhiwei
 */
@RestControllerAdvice(assignableTypes = VerifyCodeController.class)
public class VerifyCodeControllerAdvice {

    @ExceptionHandler
    public HttpEntity verifyFailedExceptionHandler(VerifyFailedException e, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setStatus(HttpStatus.FORBIDDEN.value());
        errorDetails.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
        errorDetails.setMessage(e.getLocalizedMessage());
        errorDetails.setPath(request.getServletPath());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDetails);
    }
}
