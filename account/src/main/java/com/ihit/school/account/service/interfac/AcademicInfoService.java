package com.ihit.school.account.service.interfac;

import com.ihit.school.account.model.AcademicInfo;

import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 07/Jul/2019.
 */
public interface AcademicInfoService {
    List allAcademicInfo();
    AcademicInfo findById(Integer id);
}
