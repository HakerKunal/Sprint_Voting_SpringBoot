package com.project.sprintvotingapp.dto;

import java.time.LocalDate;

public class SprintRequestDTO {

    private String sprintName;


    private LocalDate startDate;



    private LocalDate endDate;


    private boolean isActive;

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSprintName() {
        return sprintName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
