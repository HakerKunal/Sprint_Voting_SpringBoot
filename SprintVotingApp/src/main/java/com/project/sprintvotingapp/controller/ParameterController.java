package com.project.sprintvotingapp.controller;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.dto.ParameterRequestDTO;
import com.project.sprintvotingapp.dto.SprintRequestDTO;
import com.project.sprintvotingapp.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @PostMapping("/parameters")
    public ResponseEntity<APIResponse> addParameter(@RequestBody ParameterRequestDTO parameterRequestDTO) {

        APIResponse apiResponse = parameterService.addParameter(parameterRequestDTO);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }
    @PutMapping("/parameters/{id}")
    public ResponseEntity<APIResponse> updateParameter(@RequestBody ParameterRequestDTO parameterRequestDTO, @PathVariable("id") int id) {

        APIResponse apiResponse = parameterService.updateParameter(parameterRequestDTO,id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    @DeleteMapping("/parameters/{id}")
    public ResponseEntity<APIResponse> deleteParameter(@PathVariable("id") int id) {

        APIResponse apiResponse = parameterService.deleteParameter(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    @GetMapping("/parameters")
    public ResponseEntity<APIResponse> getAllParameters() {

        APIResponse apiResponse = parameterService.getAllParameter();
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    @GetMapping("/parameters/{id}")
    public ResponseEntity<APIResponse> getParameter(@PathVariable int id) {

        APIResponse apiResponse = parameterService.getParameter(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
