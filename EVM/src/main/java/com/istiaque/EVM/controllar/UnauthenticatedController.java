package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.UserRequest;
import com.istiaque.EVM.service.BrowserService;
import com.istiaque.EVM.service.UserRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@Slf4j
@Controller
public class UnauthenticatedController {
    @Autowired
    BrowserService browserService;
    @Autowired
    UserRequestService userRequestService;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        if (browserService.isNotRegister(request)) {
            Cookie cookie = browserService.newBrowserId("_bid");
            response.addCookie(cookie);
            browserService.saveBrowser(request, cookie.getValue());
        }
        modelAndView.setViewName("unauthenticatedPage/login");
        return modelAndView;
    }

    @GetMapping("/singup")
    public ModelAndView singup(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        if (browserService.isNotRegister(request)) {
            Cookie cookie = browserService.newBrowserId("_bid");
            response.addCookie(cookie);
            browserService.saveBrowser(request, cookie.getValue());
        }
        modelAndView.setViewName("unauthenticatedPage/singup");
        return modelAndView;
    }

    @PostMapping("/singup")
    public ModelAndView userRequest(ModelAndView modelAndView, @ModelAttribute("user") UserRequest user, HttpServletRequest request) {
        user.setBid(browserService.cookieValue(request, "_bid"));
        userRequestService.saveUserRequest(user);
        browserService.saveBrowserUserName(request, user.getEmail());
        log.info("new user request receive from : "+user.getEmail());
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
