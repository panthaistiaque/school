package com.ihit.school.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 01/Jul/2019.
 */
@Data
@Entity
@Table(name = "stu_academic_info")
public class AcademicInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acaId;
    private Integer acaLevel;
    private char acaSection;
    private Integer acaRoll;
    private Date acaAdmissionDate;
    private String acaGuidTeacher;
    private String acaComment;
    @ManyToOne
    @JoinColumn(name = "stu_id")
    private Student student_academicInfo;
}
