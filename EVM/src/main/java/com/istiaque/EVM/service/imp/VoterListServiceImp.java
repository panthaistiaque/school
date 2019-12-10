package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Election;
import com.istiaque.EVM.model.VoterList;
import com.istiaque.EVM.repository.ElectionRepository;
import com.istiaque.EVM.repository.VoterListRepository;
import com.istiaque.EVM.service.VoterListService;
import com.istiaque.EVM.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void save(String code, Integer userId) {
        Election election = electionRepository.findAllByElectionCode(code);
        List<VoterList> list = findAllByUserId(userId);
        if (election != null) {
            if(list!=null){
                for (VoterList voterList: list ) {
                    if(voterList.getElection().getElectionCode().equalsIgnoreCase(code)){
                        log.warn("illegal try for voter list entry");
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
        }
    }

    @Override
    public List<VoterList> findAllByUserId(Integer userId) {
        return  voterListRepository.findAllByUserId(userId);
    }
}
