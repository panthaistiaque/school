package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.User;
import com.istiaque.EVM.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 12/13/2019.
 */
@Slf4j
@Controller
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @ResponseBody
    @GetMapping("/notifi")
    public List getAllUnreadNotification(@AuthenticationPrincipal User user) {
        return notificationService.findAllUnreadNotification(user.getId());
    }

    @GetMapping("/all_notification")
    public ModelAndView getAllNotification(ModelAndView modelAndView,@AuthenticationPrincipal User user){
        List list = notificationService.findAllUnreadNotification(user.getId());
        Collections.sort(list);
        modelAndView.addObject("notice", list);
        modelAndView.addObject("readNotice", notificationService.findAllReadNotification(user.getId()));
        modelAndView.setViewName("commonPage/notification");
        return modelAndView;
    }
    @ResponseBody
    @PostMapping("/notification_seen")
    public void notificationStatusChange(@AuthenticationPrincipal User user, HttpServletRequest request){
        try {
            notificationService.clickStatusChange(Integer.valueOf(request.getParameter("noti_i")),user.getId());
        }catch (Exception e){
            log.error("notification Status Change problem, For notification id: "+request.getParameter("noti_i"));
        }
    }
}
