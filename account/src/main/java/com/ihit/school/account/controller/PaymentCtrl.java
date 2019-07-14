package com.ihit.school.account.controller;

import com.ihit.school.account.model.Stmt;
import com.ihit.school.account.service.interfac.StmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 12/Jul/2019.
 */
@Controller
public class PaymentCtrl {
    @Autowired
    StmtService stmtService;

    @GetMapping("/paydetails/{id}")
    public ModelAndView paymentDetails(ModelAndView modelAndView, @PathVariable Integer id) {
        List<Stmt> stmtList = new ArrayList<>();
        stmtList = stmtService.findAllByAcaId(id);
        modelAndView.addObject("payDetails", stmtList);
        modelAndView.setViewName("paymentDetails");
        return modelAndView;
    }
}
