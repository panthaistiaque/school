package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.VoterTurnout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 12/27/2019.
 */
@Repository
public interface VoterTurnoutRepository extends JpaRepository<VoterTurnout, Integer> {
    VoterTurnout findByElectionCodeAndUserId(String electionCode,Integer userId);
}
