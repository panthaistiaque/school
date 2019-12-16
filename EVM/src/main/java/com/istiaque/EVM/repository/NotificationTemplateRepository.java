package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 12/14/2019.
 */
@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Integer> {
}
