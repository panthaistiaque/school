package com.istiaque.EVM.repository;

import com.istiaque.EVM.model.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 11/23/2019.
 */
@Repository
public interface AssociationRepository extends JpaRepository<Association, Integer>{
}
