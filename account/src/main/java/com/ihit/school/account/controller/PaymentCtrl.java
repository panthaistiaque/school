package com.ihit.school.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 12/Jul/2019.
 */
@Controller
public class PaymentCtrl {
    @GetMapping("/paydetails")
    public ModelAndView paymentDetails(ModelAndView modelAndView){
        modelAndView.setViewName("studentList");
        return modelAndView;
    }
}
