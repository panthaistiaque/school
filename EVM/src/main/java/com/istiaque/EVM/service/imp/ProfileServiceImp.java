package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.repository.ProfileRepository;
import com.istiaque.EVM.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 11/18/2019.
 */
@Service
public class ProfileServiceImp implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile findByUser(User user) {
        return profileRepository.findByUser(user);
    }
}
