package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {
//    UserRequest findByUserName(String );
}
