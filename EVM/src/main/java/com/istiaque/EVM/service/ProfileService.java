package com.istiaque.EVM.service;

import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.User;

/**
 * Created by User on 11/18/2019.
 */
public interface ProfileService {
    Profile save(Profile profile);
    Profile findByUser(User user);
}
