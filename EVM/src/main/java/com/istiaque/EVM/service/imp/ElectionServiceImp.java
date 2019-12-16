package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Association;
import com.istiaque.EVM.model.Election;
import com.istiaque.EVM.model.ElectionSchedule;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.AssociationRepository;
import com.istiaque.EVM.repository.ElectionRepository;
import com.istiaque.EVM.service.ElectionService;
import com.istiaque.EVM.service.NotificationService;
import com.istiaque.EVM.util.ConstantUtil;
import com.istiaque.EVM.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.istiaque.EVM.util.DateUtil.*;


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
    @Autowired
    NotificationService notificationService;
    @Override
    public Association saveAssociation(Association association) {
        log.info("New Association save for : " + association.getAssociationName() + " by user id :" + association.getCreatedBy());
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
        String[] dateArray = election.getValidDate().split("-");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date =  sdf1.parse(election.getValidDate());

        boolean marker = true;
        while (marker) {
            String s = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            if (!electionRepository.existsByElectionCode(s)) {
                election.setElectionCode(s);
                marker = false;
            }
        }
        election.setCreationDate(currentDateTime());
        election.setStatus(Status.OPEN);
//        --------------------electionSchedule---------------------
        ElectionSchedule electionSchedule = new ElectionSchedule();
        electionSchedule.setNominationStartDate(currentDate());
        electionSchedule.setNominationCloseDate(nominationCloseDate(date));
        electionSchedule.setElectionDate(electionDate(date));
        electionSchedule.setResultPublishDate(electionResultDate(date));
//        ------------------------------------------
        election.setElectionSchedule(electionSchedule);
        log.info("New Election save for : " + election.getElectionCode() + " by user id :" + election.getCreatedBy());
        election = electionRepository.save(election);
        notificationService.save(ConstantUtil.ELECTION_CREATE_NOTIFICATION,election.getCreatedBy(),new String[]{election.getElectionName(),election.getAssociation().getAssociationName(),election.getValidDate()});
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return election;
    }

    @Override
    public List getAllOpenElection() {
        return electionRepository.findAllByStatus(Status.OPEN);
    }
}
