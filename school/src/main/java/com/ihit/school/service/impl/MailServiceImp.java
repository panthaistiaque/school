package com.ihit.school.service.impl;

import com.ihit.school.model.Users;
import com.ihit.school.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 28/Sep/2019.
 */
@Service
public class MailServiceImp implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sentActivationMail(Users user, HttpServletRequest request) {
        String s = "";
        String s1 = user.getEmail();
        Context context = new Context();
        Map model = new HashMap();
        model.put("name", user.getFirstName() + " " + user.getLastName());
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("actionPath", url + "/resetpassword");
        model.put("activationToken", "111111");//Aes.encrypt(login.getActivationToken()));
        model.put("unserName", "111111");//Aes.encrypt(login.getUserName()));
        model.put("activatiionTime", "222222");//Aes.encrypt(new Date().toString()));
        model.put("type", "New User");
        context.setVariables(model);
        String html = templateEngine.process("email_template", context);

        sentMailService("Welcome in our service", s, s1, html);
    }

    public void sentMailService(String subject, String body, String receiver, String html) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

//            helper.addAttachment("logo.png", new ClassPathResource("static/images/paper-plane.png"));
            helper.setTo(receiver);
            helper.setText(html, true);
            helper.setSubject(subject);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
