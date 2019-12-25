package com.istiaque.EVM.service;

import com.istiaque.EVM.model.VoterList;

import java.util.List;

/**
 * Created by User on 12/8/2019.
 */
public interface VoterListService {
    void save(String code, Integer userId);
    List<VoterList> findAllByUserId(Integer userId);
    List<VoterList> nominationAbleElectionList(Integer userId);
}
