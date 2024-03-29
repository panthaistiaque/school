package com.istiaque.EVM.service;

import com.istiaque.EVM.model.RequestRejectionMesg;
import com.istiaque.EVM.model.UserRequest;

import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
public interface UserRequestService {
    UserRequest saveUserRequest(UserRequest userRequest);
    List findAllUnaproveUser();
    UserRequest findById(Integer id);
    RequestRejectionMesg getRejectionMessage(Long id);
    String userRequestApproved(UserRequest UserRequest);
    boolean isUserExist(String email);
}
