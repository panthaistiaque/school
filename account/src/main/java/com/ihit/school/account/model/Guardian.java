package com.ihit.school.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 01/Jul/2019.
 */
@Data
@Entity
@Table(name = "stu_guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gurId;
    private String gurName;
    private String gurEmail;
    private String gurPhone;
    private String gurAddress;
    private String gurRelation;
    private Date gurEntryDate;
    @ManyToOne
    @JoinColumn(name = "stu_id")
    private Student student_guardian;
}
