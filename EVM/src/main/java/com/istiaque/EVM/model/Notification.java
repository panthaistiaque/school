package com.istiaque.EVM.model;

import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.util.DateUtil;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by User on 12/13/2019.
 */
@Data
@Entity
@Table(name = "tbl_notification")
public class Notification implements Comparable<Notification> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    @Column(length = 35)
    private String messageSummary;
    private String message;
    private String action;
    @Column(columnDefinition = "DATETIME", length = 20)
    private String creationDate= DateUtil.currentDateTime();
    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private Status isClick=Status.valueOf("NO");
    @Column(columnDefinition = "DATETIME", length = 20)
    private String clickDate;

    @Override
    public int compareTo(Notification o) {
        return getId().compareTo(o.getId());
    }


}
