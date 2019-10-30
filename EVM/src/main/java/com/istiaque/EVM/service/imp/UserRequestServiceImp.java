package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.RequestRejectionMesg;
import com.istiaque.EVM.model.UserRequest;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.RequestRejectionMesgRepository;
import com.istiaque.EVM.repository.UserRequestRepository;
import com.istiaque.EVM.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@Service
public class UserRequestServiceImp implements UserRequestService {
    @Autowired
    UserRequestRepository userRequestRepository;
    @Autowired
    RequestRejectionMesgRepository requestRejectionMesgRepository;

    @Override
    public UserRequest saveUserRequest(UserRequest userRequest) {
        userRequest.setStatus(Status.PENDING);
        return userRequestRepository.save(userRequest);
    }

    @Override
    public List findAllUnaproveUser() {
        return userRequestRepository.findAll();
    }

    @Override
    public UserRequest findById(Integer id) {
        return userRequestRepository.findById(Long.valueOf(id)).get();
    }

    @Override
    public RequestRejectionMesg getRejectionMessage(Integer id) {
        return requestRejectionMesgRepository.findByRequestId(id);
    }
}
