package com.istiaque.EVM.service;

import com.istiaque.EVM.model.Notification;

import java.util.List;

/**
 * Created by User on 12/13/2019.
 */
public interface NotificationService {
    void save(Integer type,Integer userId, String[] message);
    List findAllUnreadNotification(Integer userId);
    List findAllReadNotification(Integer userId);
    void clickStatusChange(Integer id,Integer userId);
}
