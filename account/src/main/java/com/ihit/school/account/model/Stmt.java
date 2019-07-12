package com.ihit.school.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 12/Jul/2019.
 */
@Data
@Entity
@Table(name = "acc_stmt")
public class Stmt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "aca_id")
//    @Column(name = "aca_id")
    private AcademicInfo academicInfo;
    @ManyToOne
    @JoinColumn(name = "des_id")
    private PaymentDescription paymentDescription;
    private Date payDate;
    private Integer receivedBy;
    private Integer authorizeBy;
}
