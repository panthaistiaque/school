package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Candidate;
import com.istiaque.EVM.model.VoterList;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.CandidateRepository;
import com.istiaque.EVM.repository.VoterListRepository;
import com.istiaque.EVM.service.NominationService;
import com.istiaque.EVM.service.NotificationService;
import com.istiaque.EVM.util.ConstantUtil;
import com.istiaque.EVM.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 12/25/2019.
 */
@Service
public class NominationServiceImp implements NominationService {
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    VoterListRepository voterListRepository;
    @Autowired
    NotificationService notificationService;

    @Override
    public void acceptNomination(Long candidateId, Long supporterId, Integer sl, String status) {
        Candidate candidate = candidateRepository.findByCandidateIdAndSupporterIdAndStatusAndElectionElectionScheduleNominationCloseDateGreaterThanEqualAndVoterListVoterNo(sl, supporterId, Status.PENDING, DateUtil.currentDate(), candidateId);
        if (status.equals(Status.APPROVED.toString()) && candidate != null) {
            candidate.setStatus(Status.APPROVED);
            candidate.setSupporterApproveDate(DateUtil.currentDateTime());
            candidateRepository.save(candidate);
            notificationService.save(ConstantUtil.NOMINATION_APPROVED_BY_SUPPORTER,voterListRepository.findByVoterNo(candidateId).getUserId(),new String[]{candidate.getElection().getElectionName(),candidate.getElection().getAssociation().getAssociationName()});
        } else if (status.equals(Status.REJECTED.toString()) && candidate != null) {
            candidate.setStatus(Status.REJECTED);
            candidate.setSupporterApproveDate(DateUtil.currentDateTime());
            candidateRepository.save(candidate);
            VoterList voterList = voterListRepository.findByVoterNo(supporterId);
            voterList.setIsSupporter(Status.NO);
            voterListRepository.save(voterList);
            notificationService.save(ConstantUtil.NOMINATION_REJECT_BY_SUPPORTER,voterListRepository.findByVoterNo(candidateId).getUserId(),new String[]{candidate.getElection().getElectionName(),candidate.getElection().getAssociation().getAssociationName()});
        }
    }
}
