package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 12/12/2019.
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
