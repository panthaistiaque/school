package com.ihit.school.controllar;

import com.ihit.school.model.Users;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 23/Sep/2019.
 */
@Controller
public class Index {
    @GetMapping("/login")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("singin");
        return modelAndView;
    }

    //    @ResponseBody
    @GetMapping({"/home","/"})
    public ModelAndView home(ModelAndView modelAndView, @AuthenticationPrincipal Users currentUser) {
        modelAndView.addObject("menu", currentUser.getMenu());
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }
}
