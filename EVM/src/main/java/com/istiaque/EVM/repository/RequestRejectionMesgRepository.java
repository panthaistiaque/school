package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.RequestRejectionMesg;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 30/Oct/2019.
 */
public interface RequestRejectionMesgRepository extends JpaRepository<RequestRejectionMesg, Integer> {
    RequestRejectionMesg findByRequestId(Long requestId);
}
