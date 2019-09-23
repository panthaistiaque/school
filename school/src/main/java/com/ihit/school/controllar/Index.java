package com.ihit.school.controllar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

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
    @ResponseBody
    @GetMapping("/home")
    public List home(ModelAndView modelAndView) {
//        modelAndView.setViewName("singin");
        return Arrays.asList(new String[]{"Apple", "Orang"});
    }
}
