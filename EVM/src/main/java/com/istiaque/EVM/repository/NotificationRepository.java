package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.Notification;
import com.istiaque.EVM.model.enam.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 12/13/2019.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List findAllByUserIdAndIsClick(Integer userId, Status isClick);
    Notification findByIdAndUserIdAndIsClick(Integer id,Integer userId, Status isClick);
}
