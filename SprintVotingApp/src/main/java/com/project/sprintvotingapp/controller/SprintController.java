package com.project.sprintvotingapp.controller;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.dto.SprintRequestDTO;
import com.project.sprintvotingapp.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @PostMapping("/sprints")
    public ResponseEntity<APIResponse> addSprint(@RequestBody SprintRequestDTO sprintRequestDTO) {

        APIResponse apiResponse = sprintService.addSprint(sprintRequestDTO);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping("/sprints/{id}")
    public ResponseEntity<APIResponse> addSprint(@RequestBody SprintRequestDTO sprintRequestDTO, @PathVariable("id") int id) {

        APIResponse apiResponse = sprintService.updateSprint(sprintRequestDTO, id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @DeleteMapping("/sprints/{id}")
    public ResponseEntity<APIResponse> deleteSprint(@PathVariable("id") int id) {

        APIResponse apiResponse = sprintService.deleteSprint(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/sprints")
    public ResponseEntity<APIResponse> getAllSprint() {

        APIResponse apiResponse = sprintService.getAllSprint();
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/sprints/{id}")
    public ResponseEntity<APIResponse> getSprint(@PathVariable int id) {

        APIResponse apiResponse = sprintService.getSprint(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
