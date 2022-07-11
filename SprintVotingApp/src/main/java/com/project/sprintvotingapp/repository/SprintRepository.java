package com.project.sprintvotingapp.repository;

import com.project.sprintvotingapp.entity.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface SprintRepository extends CrudRepository<Sprint, Integer> {
    Sprint findBySprintName(String sprintname);
    Sprint findBySprintId(int sprintid);

}
