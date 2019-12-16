package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.UserRequest;
import com.istiaque.EVM.service.BrowserService;
import com.istiaque.EVM.service.UserRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, @ModelAttribute("mess") String s) {
        if (browserService.isNotRegister(request)) {
            Cookie cookie = browserService.newBrowserId("_bid");
            response.addCookie(cookie);
            browserService.saveBrowser(request, cookie.getValue());
        }
        if (error != null) {
            modelAndView.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (logout != null) {
            modelAndView.addObject("message", "Logged out successfully.");
        }
        if(logout == null && s!=null ){
            modelAndView.addObject("message", s);
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
    public ModelAndView userRequest(ModelAndView modelAndView, @ModelAttribute("user") UserRequest user, HttpServletRequest request, RedirectAttributes redirect) {
        user.setBid(browserService.cookieValue(request, "_bid"));
        userRequestService.saveUserRequest(user);
        browserService.saveBrowserUserName(request, user.getEmail());
        log.info("new user request receive from : " + user.getEmail());
        redirect.addFlashAttribute("mess","After approve your request , you will get a mail.");
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception != null) {
            if (exception instanceof BadCredentialsException) {
                error = "Invalid username or password !";
//            } else if (exception.getMessage() != null && exception.getMessage().equals("LockedException")) {
//                error = "Your IP/Uesr Block For 10 Min.!";
//            } else if (exception.getMessage() != null && exception.getMessage().equals("captchaError")) {
//                error = "Captcha code not match!";
            } else {
                log.error(exception.getMessage());
                error = "Invalid username or password !!";
            }
        }
        return error;
    }
}
