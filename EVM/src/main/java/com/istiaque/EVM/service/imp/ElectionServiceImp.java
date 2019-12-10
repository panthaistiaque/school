package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Association;
import com.istiaque.EVM.model.Election;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.AssociationRepository;
import com.istiaque.EVM.repository.ElectionRepository;
import com.istiaque.EVM.service.ElectionService;
import com.istiaque.EVM.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by User on 11/23/2019.
 */
@Slf4j
@Service
public class ElectionServiceImp implements ElectionService {
    @Autowired
    AssociationRepository associationRepository;
    @Autowired
    ElectionRepository electionRepository;

    @Override
    public Association saveAssociation(Association association) {
        log.info("New Association save for : "+association.getAssociationName() +" by user id :"+association.getCreatedBy());
        return associationRepository.save(association);
    }

    @Override
    public List getAssociation() {
        List list = associationRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    @Override
    public Election save(Election election) {
        boolean marker = true;
        while (marker) {
            String s = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            if (!electionRepository.existsByElectionCode(s)) {
                election.setElectionCode(s);
                marker = false;
            }
        }
        election.setCreationDate(DateUtil.currentDateTime());
        election.setStatus(Status.OPEN);
        log.info("New Election save for : "+election.getElectionCode() +" by user id :"+election.getCreatedBy());
        return electionRepository.save(election);
    }

    @Override
    public List getAllOpenElection() {
        return electionRepository.findAllByStatus(Status.OPEN);
    }
}
