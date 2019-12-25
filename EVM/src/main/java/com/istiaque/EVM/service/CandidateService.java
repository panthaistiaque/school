package com.istiaque.EVM.service;

import com.istiaque.EVM.model.Candidate;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 12/12/2019.
 */
public interface CandidateService {
    Candidate saveCandidate(long candidateId, long supporterId, HttpSession session);
    Candidate findByElectionElectionIdAndVoterListVoterNo(Integer electionId, Long voterNo);
    List getAllCandidate(Integer electionId);
}
