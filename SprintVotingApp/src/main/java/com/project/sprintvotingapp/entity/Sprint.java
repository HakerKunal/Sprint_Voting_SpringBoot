package com.project.sprintvotingapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="sprint_tbl")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int sprintId;

    private String sprintName;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


    private boolean isActive;




    public boolean isActive() {
        return isActive;
    }

    public int getSprintId() {
        return sprintId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

