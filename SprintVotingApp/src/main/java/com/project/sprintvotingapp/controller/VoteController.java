package com.project.sprintvotingapp.controller;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping("/votes/{id}")
    public ResponseEntity<APIResponse> addVotes(@RequestBody HashMap parameterList,@PathVariable int id){
        APIResponse apiResponse=voteService.addVotes(parameterList,id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/votes/{id}/{vote_by}")
    public ResponseEntity<APIResponse> getVotes(@PathVariable("id") int id,@PathVariable("vote_by") int vote_by){
        APIResponse apiResponse=voteService.getVotes(id,vote_by);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }

    @GetMapping("/admin/results/{id}")
    public ResponseEntity<APIResponse> getResult(@PathVariable("id") int id){
        APIResponse apiResponse=voteService.getResult(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }
}
