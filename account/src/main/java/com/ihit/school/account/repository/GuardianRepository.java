package com.ihit.school.account.repository;

import com.ihit.school.account.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 07/Jul/2019.
 */
@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Integer> {
}
