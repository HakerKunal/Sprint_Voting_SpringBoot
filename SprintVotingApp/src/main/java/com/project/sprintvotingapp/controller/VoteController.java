package com.project.sprintvotingapp.controller;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.service.VoteService;
import com.project.sprintvotingapp.utils.InsertionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class VoteController {
    @Autowired
    private VoteService voteService;
    @PostMapping("/votes/{id}")
    public ResponseEntity<APIResponse> addVotes(@RequestBody HashMap parameterList, @PathVariable int id) throws InsertionException {

        try {
            APIResponse apiResponse = voteService.addVotes(parameterList, id);

            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
        } catch (InsertionException e) {
            APIResponse apiResponse=new APIResponse();
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setError(e.getMessage());
            apiResponse.setMessage("Exception Occurred");
            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
        }
    }
    @PutMapping("/votes/{id}")
    public ResponseEntity<APIResponse> updateVotes(@RequestBody HashMap parameterList, @PathVariable int id) throws InsertionException {

        try {
            APIResponse apiResponse = voteService.updateVotes(parameterList, id);

            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
        } catch (InsertionException e) {
            APIResponse apiResponse=new APIResponse();
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setError(e.getMessage());
            apiResponse.setMessage("Exception Occurred");
            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
        }
    }

    @GetMapping("/votes/{id}/{vote_by}")
    public ResponseEntity<APIResponse> getVotes(@PathVariable("id") int id, @PathVariable("vote_by") int vote_by) {
        APIResponse apiResponse = voteService.getVotes(id, vote_by);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }

    @GetMapping("/admin/results/{id}")
    public ResponseEntity<APIResponse> getResult(@PathVariable("id") int id) {
        APIResponse apiResponse = voteService.getResult(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }
}
