package com.ihit.school.account.repository;

import com.ihit.school.account.model.AcademicInfo;
import com.ihit.school.account.model.Stmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 12/Jul/2019.
 */
@Repository
public interface StmtRepository extends JpaRepository<Stmt, Integer> {
    List findAllByAcademicInfo(AcademicInfo acaId);
}
