package com.istiaque.EVM.service;

import com.istiaque.EVM.model.Association;
import com.istiaque.EVM.model.Election;

import java.util.List;

/**
 * Created by User on 11/23/2019.
 */
public interface ElectionService {
    Association saveAssociation(Association association);
    List getAssociation();
    Election save(Election election);
    List getAllOpenElection();
}
