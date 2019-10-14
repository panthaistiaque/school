package com.ihit.school.repository;

import com.ihit.school.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Sep/2019.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List findAllByRoleName(String roleName);
}
