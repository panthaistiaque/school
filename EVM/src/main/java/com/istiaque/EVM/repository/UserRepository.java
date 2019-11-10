package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 02/Nov/2019.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    User findByToken(String token);
}
