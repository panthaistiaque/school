package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.Ballot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 12/26/2019.
 */
@Repository
public interface BallotRepository extends JpaRepository<Ballot, Integer> {
    Ballot findByCandidateCandidateId(Integer candidateId);

}
