package com.istiaque.EVM.controllar;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by User on 11/16/2019.
 */
@Controller
public class ExceptionController implements ErrorController {
    @GetMapping("/403")
    public ModelAndView accessDenied(ModelAndView modelAndView) {
        modelAndView.setViewName("exception/403");
        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView resourceNotFound(ModelAndView modelAndView) {
        modelAndView.setViewName("exception/404");
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
