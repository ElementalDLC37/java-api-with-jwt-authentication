package com.vitrum.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.vitrum.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    UserDetails findByName(String name);
}
