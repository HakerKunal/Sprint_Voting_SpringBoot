package com.project.sprintvotingapp;

import com.project.sprintvotingapp.entity.User;
import com.project.sprintvotingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EntityScan(basePackages = "com.project")
public class SprintVotingAppApplication {
    @Autowired
    private UserRepository repository;
//    @PostConstruct
//    public void initUsers() {
//        List<User> users = Stream.of(
//                new User(101, "user1", "password", "user1@gmail.com", "user1", "batham","normal",true),
//                new User(101, "user2", "password", "user2@gmail.com", "user1", "batham","admin",true)
//        ).collect(Collectors.toList());
//        repository.saveAll(users);
//    }

    public static void main(String[] args) {
        SpringApplication.run(SprintVotingAppApplication.class, args);
    }

}
