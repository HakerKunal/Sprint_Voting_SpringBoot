package com.project.sprintvotingapp.service;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.dto.SprintRequestDTO;
import com.project.sprintvotingapp.entity.Sprint;
import com.project.sprintvotingapp.entity.User;
import com.project.sprintvotingapp.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SprintService {
    @Autowired
    private SprintRepository sprintRepository;


    public APIResponse addSprint(SprintRequestDTO sprintRequestDTO) {

        APIResponse apiResponse = new APIResponse();
        Sprint sprint = sprintRepository.findBySprintName(sprintRequestDTO.getSprintName());
        System.out.println(sprint);
        if (sprint != null) {
            apiResponse.setError("Sprint Present  with the same name .Please Change the name");
            apiResponse.setMessage("Sprint Addition Unsuccessful");
            apiResponse.setStatus(400);
            return apiResponse;


        }
        Sprint sprint1 = new Sprint();
        sprint1.setSprintName(sprintRequestDTO.getSprintName());
        sprint1.setStartDate(sprintRequestDTO.getStartDate());
        sprint1.setEndDate(sprintRequestDTO.getEndDate());
        sprint1.setActive(true);
        boolean result = sprint1.getStartDate().isAfter(sprint1.getEndDate());
        if (result) {
            apiResponse.setError("Start date should be less than End Date");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Sprint Addition Unsuccessful");
            return apiResponse;
        }
        sprint1 = sprintRepository.save(sprint1);
        apiResponse.setData(sprint1);
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setMessage("Sprint Addition Successful");
        return apiResponse;
    }

    public APIResponse updateSprint(SprintRequestDTO sprintRequestDTO, int sprintId) {
        APIResponse apiResponse = new APIResponse();

        Sprint extSprint = sprintRepository.findBySprintId(sprintId);
        if (extSprint == null) {
            apiResponse.setError("Sprint with the given id not found");
            apiResponse.setMessage("Sprint Updation Unsuccessful");
            apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return apiResponse;

        }
        Sprint sprint = new Sprint();
        sprint.setSprintId(sprintId);
        sprint.setSprintName(sprintRequestDTO.getSprintName());
        sprint.setStartDate(sprintRequestDTO.getStartDate());
        sprint.setEndDate(sprintRequestDTO.getEndDate());
        sprint.setActive(sprintRequestDTO.isActive());

        sprint = sprintRepository.save(sprint);
        apiResponse.setData(sprint);
        apiResponse.setMessage("Sprint Updation Successful");

        return apiResponse;

    }

    public APIResponse deleteSprint(int sprintId){
        APIResponse apiResponse = new APIResponse();

        Sprint extSprint = sprintRepository.findBySprintId(sprintId);
        if (extSprint == null) {
            apiResponse.setError("Sprint with the given id not found");
            apiResponse.setMessage("Sprint Deletion Unsuccessful");
            apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return apiResponse;

        }
        sprintRepository.deleteById(sprintId);
        apiResponse.setMessage("Sprint Deletion Successful");
        return apiResponse;

    }

    public APIResponse getAllSprint(){
        APIResponse apiResponse=new APIResponse();
        List<Sprint> list= (List<Sprint>) sprintRepository.findAll();
        apiResponse.setData(list);
        apiResponse.setMessage("Here is your sprints");
        return apiResponse;
    }

    public APIResponse getSprint(int sprintId){
        APIResponse apiResponse = new APIResponse();

        Sprint extSprint = sprintRepository.findBySprintId(sprintId);
        if (extSprint == null) {
            apiResponse.setError("Sprint with the given id not found");
            apiResponse.setMessage("Sprint Deletion Unsuccessful");
            apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return apiResponse;

        }
        Sprint sprint=sprintRepository.findBySprintId(sprintId);
        apiResponse.setData(sprint);
        apiResponse.setMessage("Here Your Sprint");
        return apiResponse;


    }
}
