package com.istiaque.EVM.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 01/Nov/2019.
 */
@Slf4j
@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender sender;
    @Value("${application.mail.icon.path}")
    private String pic_dir;
    private String approvalMessage = "<html>\n" +
            " <body > \n" +
            "\t<table style='min-width:348px' width='100%' lang='en' height='100%' cellspacing='0' cellpadding='0' border='0'>\n" +
            "\t\t<tbody>\n" +
            "\t\t\t<tr align='center'>\n" +
            "\t\t\t\t<td>\n" +
            "\t\t\t\t\t<table style='padding-bottom:20px;max-width:516px;min-width:220px' cellspacing='0' cellpadding='0' border='0'>\n" +
            "\t\t\t\t\t\t<tbody>\n" +
            "\t\t\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t\t\t<img src='cid:id101' aria-hidden='true' style='margin-bottom:16px' width='74' height='24'>\n" +
            "\t\t\t\t\t\t\t\t<div style='border-style:solid;border-width:thin;border-color:#dadce0;border-radius:8px;padding:40px 20px'  align='center'>\n" +
            "\t\t\t\t\t\t\t\t\t<div style='font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;border-bottom:thin solid #dadce0;color:rgba(0,0,0,0.87);line-height:32px;padding-bottom:24px;text-align:center;word-break:break-word'>\n" +
            "\t\t\t\t\t\t\t\t\t\t<div style='font-size:24px;'>User Request is Approved&nbsp;</div> \n" +
            "\t\t\t\t\t\t\t\t\t\t<hr/>\n" +
            "\t\t\t\t\t\t\t\t\t\t<div style=\"font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:14px;color:rgba(0,0,0,0.87);line-height:20px;padding-top:20px;text-align:center\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\tYour Account was created successfully. Before use your account please set password by clicking this button. \n" +
            "\t\t\t\t\t\t\t\t\t\t\t<div style=\"padding-top:32px;text-align:center\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"ENCRYPTED_TOKEN\" style=\"font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;line-height:16px;color:#ffffff;font-weight:400;text-decoration:none;font-size:14px;display:inline-block;padding:10px 24px;background-color:#E2136E;border-radius:5px;min-width:90px\" target=\"_blank\" >Set Password</a>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t</div> \n" +
            "\t\t\t\t\t\t\t\t<div style=\"font-family:Roboto-Regular,Helvetica,Arial,sans-serif;color:rgba(0,0,0,0.54);font-size:11px;line-height:18px;padding-top:12px;text-align:center\">\n" +
            "\t\t\t\t\t\t\t\t\t<div>\n" +
            "\t\t\t\t\t\t\t\t\t\tYou received this email to let you know about your E-voteing system Account and services. Please do not replay this mail.\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t<div style=\"direction:ltr\">\n" +
            "\t\t\t\t\t\t\t\t\t\t© 2019  \n" +
            "\t\t\t\t\t\t\t\t\t\t<a style=\"font-family:Roboto-Regular,Helvetica,Arial,sans-serif;color:rgba(0,0,0,0.54);font-size:11px;line-height:18px;padding-top:12px;text-align:center\">Pantha Istiaque</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</tr>\n" +
            "\t\t\t\t\t\t</tbody>\n" +
            "\t\t\t\t\t</table>\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t</tr>\n" +
            "\t\t</tbody>\n" +
            "\t</table>\n" +
            " <body>\n" +
            "</html>";

    public void manageMail(String type, String toAddress, String tokenValue) {
        switch (type) {
            case "UserApprove":
                String s = "http://localhost:8080/setpassword?tk="+tokenValue + "&v=" + String.valueOf(System.currentTimeMillis());
                System.out.println(s);
                log.info(s);
                sendEmail(approvalMessage.replace("ENCRYPTED_TOKEN", s), "Request Accepted", toAddress);
        }
    }

    private void sendEmail(String mailBody, String subject, String toAddress) {
        try {
            MimeMessage message = sender.createMimeMessage();

            // Enable the multipart flag!
            MimeMessageHelper helper = new MimeMessageHelper(message, true);


            helper.setTo(toAddress);
//        helper.setText("<html><body>Here is a cat picture! <img src='cid:id101'/><body></html>", true);
            helper.setText(mailBody, true);
            helper.setSubject(subject);

            ClassPathResource file = new ClassPathResource("evote_min.jpg");
            helper.addInline("id101", file);

            sender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
