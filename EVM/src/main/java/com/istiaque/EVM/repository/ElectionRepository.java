package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.Election;
import com.istiaque.EVM.model.enam.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 11/24/2019.
 */
@Repository
public interface ElectionRepository extends JpaRepository<Election, Integer> {
    boolean existsByElectionCode(String electionCode);
    List findAllByStatus(Status status);
    Election findAllByElectionCode(String electionCode);
}
