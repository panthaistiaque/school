package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Election;
import com.istiaque.EVM.model.VoterList;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.*;
import com.istiaque.EVM.service.NotificationService;
import com.istiaque.EVM.service.VoterListService;
import com.istiaque.EVM.util.ConstantUtil;
import com.istiaque.EVM.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by User on 12/8/2019.
 */
@Slf4j
@Service
public class VoterListServiceImp implements VoterListService {
    @Autowired
    VoterListRepository voterListRepository;
    @Autowired
    ElectionRepository electionRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public void save(String code, Integer userId) {
        Election election = electionRepository.findAllByElectionCode(code);
        List<VoterList> list = findAllByUserId(userId);
        if (election != null) {
            if (list != null) {
                for (VoterList voterList : list) {
                    if (voterList.getElection().getElectionCode().equalsIgnoreCase(code)) {
                        log.warn("illegal try for voter list entry");
                        notificationService.save(ConstantUtil.VOTER_REQUEST_REJECT_NOTIFICATION, userId, new String[]{voterList.getElection().getElectionName(), voterList.getElection().getAssociation().getAssociationName(), String.valueOf(voterList.getVoterNo())});
                        return;
                    }
                }
            }
            VoterList voterList = new VoterList();
            voterList.setElection(election);
            voterList.setCreateOn(DateUtil.currentDateTime());
            voterList.setVoterNo(DateUtil.uniqueNo());
            voterList.setUserId(userId);
            voterListRepository.save(voterList);
            notificationService.save(ConstantUtil.VOTER_REQUEST_NOTIFICATION, userId, new String[]{election.getElectionName(), election.getAssociation().getAssociationName(), String.valueOf(voterList.getVoterNo())});
        }
    }

    @Override
    public List<VoterList> findAllByUserId(Integer userId) {
        return voterListRepository.findAllByUserId(userId);
    }

    @Override
    public List<VoterList> nominationAbleElectionList(Integer userId) {
        return voterListRepository.findAllByUserIdAndElectionElectionScheduleNominationCloseDateGreaterThanEqual(userId, DateUtil.currentDate());
    }

    @Override
    public List<VoterList> voteAbleElectionList(Integer userId) {
        List list = voterListRepository.findAllByUserIdAndElectionValidDateGreaterThanEqual(userId, DateUtil.currentDate());
        return list;
    }

    @Override
    public List allCandidateListForVote(String electionCode, Integer userId) {
        VoterList voterList = voterListRepository.findByUserIdAndElectionElectionCode(userId,electionCode);
        if(voterList!=null){
            return candidateRepository.findAllByStatusAndElectionElectionCodeAndElectionStatusAndElectionElectionScheduleElectionDate(Status.APPROVED,electionCode, Status.OPEN,DateUtil.currentDate());
        }
        return null;
    }
}
