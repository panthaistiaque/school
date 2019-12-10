package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.User;
import com.istiaque.EVM.repository.UserRepository;
import com.istiaque.EVM.service.UserService;
import com.istiaque.EVM.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 09/Nov/2019.
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public User passwordUpdate(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        user.setPassword(passwordEncoder.encode(password));
        user.setPassWordUpdateDate(DateUtil.currentDate());
        user.setActive(true);
        user.setToken(null);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
