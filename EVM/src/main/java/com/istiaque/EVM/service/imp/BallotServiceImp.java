package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Ballot;
import com.istiaque.EVM.model.Candidate;
import com.istiaque.EVM.model.VoterList;
import com.istiaque.EVM.model.VoterTurnout;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.BallotRepository;
import com.istiaque.EVM.repository.CandidateRepository;
import com.istiaque.EVM.repository.VoterListRepository;
import com.istiaque.EVM.repository.VoterTurnoutRepository;
import com.istiaque.EVM.service.BallotService;
import com.istiaque.EVM.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 12/26/2019.
 */
@Service
public class BallotServiceImp implements BallotService {
    @Autowired
    BallotRepository ballotRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    VoterTurnoutRepository turnoutRepository;
    @Autowired
    VoterListRepository voterListRepository;

    @Override
    public void castBallot(Integer candidateId, Integer userId, String electionCode) {
        VoterTurnout voterTurnout = new VoterTurnout();
        VoterList voterList = voterListRepository.findByUserIdAndElectionElectionCode(userId, electionCode);
        Ballot ballot = ballotRepository.findByCandidateCandidateId(candidateId);
        if (ballot != null) {
            ballot.setCounter(ballot.getCounter() > 0 ? ballot.getCounter() + 1 : 1);
            ballotRepository.save(ballot);
        } else {
            Candidate candidate = candidateRepository.findById(candidateId).get();
            Ballot ballot1 = new Ballot();
            ballot1.setCounter(ballot1.getCounter() > 0 ? ballot1.getCounter() + 1 : 1);
            ballot1.setCandidate(candidate);
            ballot1.setStatus(Status.OPEN);
            ballot1.setIsResultPublish(Status.NO);
            ballotRepository.save(ballot1);

        }
        voterTurnout.setVoterNo(voterList.getVoterNo());
        voterTurnout.setElectionCode(electionCode);
        voterTurnout.setVotingTime(DateUtil.currentDateTime());
        voterTurnout.setUserId(userId);
        turnoutRepository.save(voterTurnout);
    }
}
