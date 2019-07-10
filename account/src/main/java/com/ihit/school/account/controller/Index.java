package com.ihit.school.account.controller;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 01/Jul/2019.
 */

import com.ihit.school.account.repository.AcademicInfoRepository;
import com.ihit.school.account.repository.GuardianRepository;
import com.ihit.school.account.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Index {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    AcademicInfoRepository academicInfoRepository;
    @Autowired
    GuardianRepository guardianRepository;

    @GetMapping(value = "/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("academicinfo", academicInfoRepository.findAll());
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
