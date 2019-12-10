package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.VoterList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 12/8/2019.
 */
@Repository
public interface VoterListRepository extends JpaRepository<VoterList, Integer> {
    List<VoterList> findAllByUserId(Integer userId);
}
