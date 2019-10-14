package com.ihit.school.service;

import com.ihit.school.model.Users;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 28/Sep/2019.
 */
public interface MailService {
    void sentActivationMail(Users user,  HttpServletRequest request);
}
