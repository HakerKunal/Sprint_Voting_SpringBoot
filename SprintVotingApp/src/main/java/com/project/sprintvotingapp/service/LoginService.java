package com.project.sprintvotingapp.service;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.dto.LoginRequestDTO;
import com.project.sprintvotingapp.dto.SignUpRequestDTO;
import com.project.sprintvotingapp.entity.User;
import com.project.sprintvotingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public APIResponse login(LoginRequestDTO loginRequestDTO) {
        APIResponse apiResponse = new APIResponse();

        User user = userRepository.findOneByUsernameIgnoreCaseAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        if (user == null) {
            apiResponse.setData("Login Failed");
            return apiResponse;
        }
        apiResponse.setData("Login Successfull");
        return apiResponse;
    }

    public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse = new APIResponse();
        User user=userRepository.findByUsername(signUpRequestDTO.getUsername());
        if (user!=null){
            apiResponse.setData("User already Exist with same username");
            apiResponse.setStatus(400);
            return apiResponse;
        }


        User userEntity = new User();
        userEntity.setUsername(signUpRequestDTO.getUsername());
        userEntity.setPassword(signUpRequestDTO.getPassword());
        userEntity.setEmail(signUpRequestDTO.getEmail());
        userEntity.setFirst_name(signUpRequestDTO.getFirst_name());
        userEntity.setLast_name(signUpRequestDTO.getLast_name());
        userEntity = userRepository.save(userEntity);
        apiResponse.setData(userEntity);
        return apiResponse;


    }
}
