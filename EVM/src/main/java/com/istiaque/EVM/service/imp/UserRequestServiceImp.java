package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.RequestRejectionMesg;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.model.UserRequest;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.repository.RequestRejectionMesgRepository;
import com.istiaque.EVM.repository.UserRepository;
import com.istiaque.EVM.repository.UserRequestRepository;
import com.istiaque.EVM.service.UserRequestService;
import com.istiaque.EVM.util.DateUtil;
import com.istiaque.EVM.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@Service
public class UserRequestServiceImp implements UserRequestService {
    @Autowired
    UserRequestRepository userRequestRepository;
    @Autowired
    RequestRejectionMesgRepository requestRejectionMesgRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    RequestRejectionMesgRepository mesgRepository;

    @Override
    public UserRequest saveUserRequest(UserRequest userRequest) {
        userRequest.setStatus(Status.PENDING);
        userRequest.setRequstDate(DateUtil.currentDate());
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
    public RequestRejectionMesg getRejectionMessage(Long id) {
        return requestRejectionMesgRepository.findByRequestId(id);
    }

    @Override
    public String userRequestApproved(UserRequest userRequest) {
        if (isUserExist(userRequest.getEmail())) {
            userRequest.setStatus(Status.REJECTED);
            userRequest.setActionDate(DateUtil.currentDate());

            RequestRejectionMesg mesg = new RequestRejectionMesg();
            mesg.setComment("User Request Rejected due to duplicate Email ID");
            mesg.setRequestId(userRequest.getRequestId());
            requestRejectionMesgRepository.save(mesg);
            userRequestRepository.save(userRequest);
            return "User Request Rejected due to duplicate Email ID";
        } else {
            User user = new User();
            user.setUserName(userRequest.getEmail());
            user.setFullName(userRequest.getFullName());
            user.setPhone(userRequest.getPhone());
            user.setDob(userRequest.getDob());
            user.setToken(UUID.randomUUID().toString());
            user.setCreationDate(DateUtil.currentDate());
            userRepository.save(user);
            emailUtil.manageMail("UserApprove", userRequest.getEmail(), user.getToken());
            userRequest.setActionDate(DateUtil.currentDate());
            userRequestRepository.save(userRequest);
            return "User Approved";
        }
    }

    @Override
    public boolean isUserExist(String email) {
//        userRepository.findByUserName(email);
        if (userRepository.findByUserName(email) != null)
            return true;
        return false;
    }
}
