package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Notification;
import com.istiaque.EVM.model.NotificationTemplate;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.NotificationRepository;
import com.istiaque.EVM.repository.NotificationTemplateRepository;
import com.istiaque.EVM.service.NotificationService;
import com.istiaque.EVM.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 12/13/2019.
 */
@Slf4j
@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationTemplateRepository templateRepository;

    @Override
    public void save(Integer type,Integer userId, String[] message) {
        NotificationTemplate template = templateRepository.findById(type).get();
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(template.getMessage());
        notification.setMessageSummary(template.getMessageSummary());
        for (int i = 0; i < message.length; i++) {
            notification.setMessage(notification.getMessage().replace(":PARAM" + i, message[i]));
            notification.setMessageSummary(notification.getMessageSummary().replace(":PARAM" + i, message[i]));
        }
        notificationRepository.save(notification);
    }

    @Override
    public List findAllUnreadNotification(Integer userId) {
        return notificationRepository.findAllByUserIdAndIsClick(userId, Status.valueOf("NO"));
    }

    @Override
    public List findAllReadNotification(Integer userId) {
        List list = notificationRepository.findAllByUserIdAndIsClick(userId, Status.valueOf("YES"));
        Collections.reverse(list);
        return list;
    }

    @Override
    public void clickStatusChange(Integer id, Integer userId) {
        Notification notification = notificationRepository.findByIdAndUserIdAndIsClick(id, userId, Status.valueOf("NO"));
        if (notification != null) {
            notification.setIsClick(Status.YES);
            notification.setClickDate(DateUtil.currentDateTime());
            notificationRepository.save(notification);
        }
    }
}
