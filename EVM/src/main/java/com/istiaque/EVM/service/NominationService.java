package com.istiaque.EVM.service;

/**
 * Created by User on 12/25/2019.
 */
public interface NominationService {
    void acceptNomination(Long candidateId, Long supporterId, Integer sl, String status);
}
