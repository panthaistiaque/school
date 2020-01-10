package com.istiaque.EVM.service;

/**
 * Created by User on 12/26/2019.
 */
public interface BallotService {
    void castBallot(Integer candidateId, Integer userId,String electionCode);
}
