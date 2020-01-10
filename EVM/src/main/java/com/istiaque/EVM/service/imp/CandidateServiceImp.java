package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Candidate;
import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.model.VoterList;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.CandidateRepository;
import com.istiaque.EVM.repository.UserRepository;
import com.istiaque.EVM.repository.VoterListRepository;
import com.istiaque.EVM.service.CandidateService;
import com.istiaque.EVM.service.NotificationService;
import com.istiaque.EVM.util.ConstantUtil;
import com.istiaque.EVM.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

import static com.istiaque.EVM.util.DateUtil.currentDate;

/**
 * Created by User on 12/12/2019.
 */
@Slf4j
@Service
public class CandidateServiceImp implements CandidateService {
    @Autowired
    VoterListRepository voterListRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationService notificationService;

    @Override
    public Candidate saveCandidate(long candidateId, long supporterId, HttpSession session) {
        VoterList candidateVoterInfo = voterListRepository.findByVoterNo(candidateId);
        if(candidateId==supporterId){
            notificationService.save(ConstantUtil.NOMINATION_REJECT_NOTIFY_SAME_ID, candidateVoterInfo.getUserId(), new String[]{candidateVoterInfo.getElection().getElectionName(), candidateVoterInfo.getElection().getAssociation().getAssociationName()});
            return null;
        }

        VoterList supporterVoterInfo = voterListRepository.findByVoterNo(supporterId);
        if (candidateVoterInfo != null && supporterVoterInfo != null) {
            if (candidateVoterInfo.getElection().getElectionId() == supporterVoterInfo.getElection().getElectionId()) {
                boolean isPreviseCandidate = candidateVoterInfo.getIsCandidate().equals(Status.valueOf("YES"));
                boolean isSupporterOfAnotherCandidate = candidateVoterInfo.getIsSupporter().equals(Status.valueOf("YES"));
                boolean doseNoteCandidate = supporterVoterInfo.getIsCandidate().equals(Status.valueOf("YES"));
                boolean doseNoteSupportOther = supporterVoterInfo.getIsSupporter().equals(Status.valueOf("YES"));
                if (!isPreviseCandidate && !isSupporterOfAnotherCandidate && candidateId != supporterId
                        && !doseNoteCandidate && !doseNoteSupportOther) {
                    candidateVoterInfo.setIsCandidate(Status.YES);
                    supporterVoterInfo.setIsSupporter(Status.YES);
                    Profile profile = (Profile) session.getAttribute("profile");
                    Candidate candidate = new Candidate();
                    candidate.setElection(candidateVoterInfo.getElection());
                    candidate.setStatus(Status.PENDING);
                    candidate.setSupporterId(supporterId);
                    candidate.setVoterList(candidateVoterInfo);
                    candidate.setName(profile.getUser().getFullName());
                    candidate.setImage(profile.getProfilePhoto());
                    candidate.setCreateOn(DateUtil.currentDateTime());
                    voterListRepository.save(candidateVoterInfo);
                    voterListRepository.save(supporterVoterInfo);
                    candidateRepository.save(candidate);
                    notificationService.save(ConstantUtil.SUPPORTER_NOTIFICATION, supporterVoterInfo.getUserId(), new String[]{profile.getUser().getFullName(), candidateVoterInfo.getElection().getElectionName(), candidateVoterInfo.getElection().getAssociation().getAssociationName()});
                    notificationService.save(ConstantUtil.CANDIDATE_WAITING_NOTIFICATION, candidateVoterInfo.getUserId(), new String[]{profile.getUser().getFullName(), candidateVoterInfo.getElection().getElectionName(), candidateVoterInfo.getElection().getAssociation().getAssociationName()});
                } else {
                    log.error("This voter already submit nomination or mark as supporter!");
                }
            } else {
                log.error("supporter id dose not found in desirae voter list! ");
                notificationService.save(ConstantUtil.NOMINATION_REJECT_NOTIFY_INVALID_SUPPORTER, candidateVoterInfo.getUserId(), new String[]{candidateVoterInfo.getElection().getElectionName(), candidateVoterInfo.getElection().getAssociation().getAssociationName(), String.valueOf(supporterId)});
            }
        }//one need in this position
        return null;
    }

    @Override
    public Candidate findByElectionElectionIdAndVoterListVoterNo(Integer electionId, Long voterNo) {

        return candidateRepository.findByElectionElectionIdAndSupporterIdAndElectionElectionScheduleNominationCloseDateGreaterThanEqual(electionId,voterNo, currentDate());
    }

    @Override
    public List getAllCandidate(Integer electionId) {
        List list = candidateRepository.findAllByElectionElectionIdAndStatusAndElectionValidDateGreaterThanEqual(electionId,Status.APPROVED, currentDate());

        return list;
    }
}
