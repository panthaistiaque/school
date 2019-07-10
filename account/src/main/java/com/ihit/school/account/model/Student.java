package com.ihit.school.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 01/Jul/2019.
 */
@Data
@Entity
@Table(name = "stu_personal")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    private Integer stuId;
    private String stuName;
    private String stuFatherName;
    private String stuMotherName;
    private String stuPresentAddress;
    private String stuPermanentAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student_guardian", orphanRemoval = true)
    private List<Guardian> GuardianList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student_academicInfo", orphanRemoval = true)
    private List<AcademicInfo> academicInfoList;
}
