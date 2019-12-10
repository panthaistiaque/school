package com.istiaque.EVM.service;

import com.istiaque.EVM.model.User;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 09/Nov/2019.
 */
public interface UserService {
    User findByToken(String token);
    User passwordUpdate(String userName, String password);
    User getUserByUserName(String userName);
}
