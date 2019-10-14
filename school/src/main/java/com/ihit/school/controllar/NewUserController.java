package com.ihit.school.controllar;

import com.ihit.school.model.Users;
import com.ihit.school.service.MailService;
import com.ihit.school.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 26/Sep/2019.
 */
@Slf4j
@Controller
public class NewUserController {
    @Autowired
    UsersService usersService;
    @Autowired
    MailService mailService;

    @GetMapping("/newAccount")
    public ModelAndView newUserRequest(ModelAndView modelAndView) {
        modelAndView.setViewName("userRequest");
        return modelAndView;
    }

    @PostMapping("/newrequest")
    public ModelAndView userRequest(ModelAndView modelAndView, @ModelAttribute("users") Users user, HttpServletRequest request) {
        try {
            if (usersService.findByEmail(user.getEmail()) == null) {
                if (usersService.save(user).getId() > 0) {
                    log.info("new temporary  user created... Email : " + user.getEmail());
                    mailService.sentActivationMail(user, request);
                    modelAndView.setViewName("redirect:/login");
                }
            } else {
                log.warn("This email already existing ... Email : " + user.getEmail());
                modelAndView.setViewName("redirect:/newAccount");
            }

        } catch (Exception e) {
            log.error("new user create request failed !! " + e.getMessage());
            modelAndView.setViewName("redirect:/newAccount");
        }
        return modelAndView;
    }
}
