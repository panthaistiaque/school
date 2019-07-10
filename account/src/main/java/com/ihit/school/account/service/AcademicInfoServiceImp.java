package com.ihit.school.account.service;

import com.ihit.school.account.repository.AcademicInfoRepository;
import com.ihit.school.account.service.interfac.AcademicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 07/Jul/2019.
 */
@Service
public class AcademicInfoServiceImp implements AcademicInfoService {
    @Autowired
    AcademicInfoRepository infoRepository;
    @Override
    public List allAcademicInfo() {
        return infoRepository.findAll();
    }
}
