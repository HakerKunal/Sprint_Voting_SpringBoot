package com.project.sprintvotingapp.controller;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.dto.LoginRequestDTO;
import com.project.sprintvotingapp.dto.SignUpRequestDTO;
import com.project.sprintvotingapp.service.LoginService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        APIResponse apiResponse = loginService.login(loginRequestDTO);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse = loginService.signUp(signUpRequestDTO);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/home")
    public ResponseEntity<APIResponse> home(){

    APIResponse apiResponse=new APIResponse();
    apiResponse.setData("home");
    return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

}
