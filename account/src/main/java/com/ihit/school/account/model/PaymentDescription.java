package com.ihit.school.account.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 12/Jul/2019.
 */
@Data
@Entity
@Table(name = "acc_payment_description")
public class PaymentDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer desId;
    private String description;
    @Column(length = 2)
    private String crDr;
}
