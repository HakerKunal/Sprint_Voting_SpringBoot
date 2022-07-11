package com.project.sprintvotingapp.service;

import com.project.sprintvotingapp.entity.User;
import com.project.sprintvotingapp.entity.UserDetailService;
import com.project.sprintvotingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepository.findByUsername(username);
        if (user ==null){
            throw new UsernameNotFoundException("No user");

        }


        return new UserDetailService(user);
    }
}
