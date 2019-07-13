package com.ihit.school.account.service;

import com.ihit.school.account.repository.StmtRepository;
import com.ihit.school.account.service.interfac.AcademicInfoService;
import com.ihit.school.account.service.interfac.StmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 12/Jul/2019.
 */
@Service
public class StmtServiceImp implements StmtService {
    @Autowired
    AcademicInfoService academicInfoService;
    @Autowired
    StmtRepository stmtRepository;

    @Override
    public List findAllByAcaId(Integer id) {
        return stmtRepository.findAllByAcademicInfo(academicInfoService.findById(id));
    }
}
