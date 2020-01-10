package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.Candidate;
import com.istiaque.EVM.model.enam.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 12/12/2019.
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate findByElectionElectionIdAndSupporterIdAndElectionElectionScheduleNominationCloseDateGreaterThanEqual(Integer electionId, Long supporterId, String nominationCloseDate);

    Candidate findByCandidateIdAndSupporterIdAndStatusAndElectionElectionScheduleNominationCloseDateGreaterThanEqualAndVoterListVoterNo(Integer candidateId, Long supporterId, Status status, String nominationCloseDate, Long voterNo);

    List findAllByElectionElectionIdAndStatusAndElectionValidDateGreaterThanEqual(Integer electionId, Status status, String validDate);

//    List findAllByStatusElectionElectionCodeElectionStatusElectionElectionScheduleElectionDate(Status status, String electionCode, Status electionStatus, String electionDate);
    List findAllByStatusAndElectionElectionCodeAndElectionStatusAndElectionElectionScheduleElectionDate(Status status, String electionCode, Status electionStatus,String electionDate);
}
