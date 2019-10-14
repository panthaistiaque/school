package com.ihit.school.service;

import com.ihit.school.model.Users;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 26/Sep/2019.
 */
public interface UsersService {
    Users findByEmail(String email);
    Users save(Users users);
}
