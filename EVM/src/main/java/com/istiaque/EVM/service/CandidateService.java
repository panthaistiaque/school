package com.istiaque.EVM.service;

import com.istiaque.EVM.model.Candidate;

import javax.servlet.http.HttpSession;

/**
 * Created by User on 12/12/2019.
 */
public interface CandidateService {
    Candidate saveCandidate(long candidateId, long supporterId, HttpSession session);
}
