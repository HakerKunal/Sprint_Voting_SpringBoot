package com.project.sprintvotingapp.repository;

import com.project.sprintvotingapp.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface UserRepository extends CrudRepository<User,Integer> {
     User findOneByUsernameIgnoreCaseAndPassword(String username,String password);
     User findByUsername(String username);
}
