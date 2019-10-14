package com.ihit.school.service.impl;

import com.ihit.school.model.Menu;
import com.ihit.school.model.Role;
import com.ihit.school.model.Users;
import com.ihit.school.repository.RoleRepository;
import com.ihit.school.repository.UserRepository;
import com.ihit.school.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 26/Sep/2019.
 */
@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Users findByEmail(String email) {
        Users users = userRepository.findByEmail(email);
        List menuList = new ArrayList<>();
        for (Role role : users.getRoleList()) {
            for (Menu menu : role.getMenuList()) {
                menuList.add(menu);
            }
        }
        users.setMenu(menuList);
        return users;
    }

    @Override
    public Users save(Users users) {
        if (users.getId() == null) {
            users.setRoleList(roleRepository.findAllByRoleName("USER"));
        }
        return userRepository.save(users);
    }
}
