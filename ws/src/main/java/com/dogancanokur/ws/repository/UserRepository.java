package com.dogancanokur.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dogancanokur.ws.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
