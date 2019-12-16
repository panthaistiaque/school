package com.istiaque.EVM.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by User on 12/14/2019.
 */
@Data
@Entity
@Table(name = "tbl_notification_template")
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String noteKey;
    @Column(length = 35)
    private String messageSummary;
    private String message;
}
