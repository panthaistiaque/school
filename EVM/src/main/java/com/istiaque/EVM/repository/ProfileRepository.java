package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 11/18/2019.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByUser(User user);
}
