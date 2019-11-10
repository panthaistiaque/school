package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.User;
import com.istiaque.EVM.repository.UserRepository;
import com.istiaque.EVM.service.UserService;
import com.istiaque.EVM.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 09/Nov/2019.
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public User passwordUpdate(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        user.setPassword(password);
        user.setPassWordUpdateDate(DateUtil.currentDate());
        user.setActive(true);
        user.setToken(null);
        return userRepository.save(user);
    }
}
