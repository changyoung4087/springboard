package org.board.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.board.commons.CommonException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("org.board.controllers")
public class CommonController {

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e, Model model, HttpServletResponse response){

        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        if(e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus().value();
        }

        response.setStatus(status);

        model.addAttribute("status", status);
        model.addAttribute("message", e.getMessage());
        model.addAttribute("exception", e);
        
        return "error/common";
    }
}
