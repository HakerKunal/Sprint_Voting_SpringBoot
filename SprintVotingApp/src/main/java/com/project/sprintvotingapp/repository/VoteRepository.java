package com.project.sprintvotingapp.repository;

import com.project.sprintvotingapp.entity.Sprint;
import com.project.sprintvotingapp.entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface VoteRepository extends JpaRepository<Votes, Integer> {
    @Query(value = "SELECT * FROM votes_tbl where vote_by_id=?1 and parameter_id_parameter_id=?2 and sprint_id_sprint_id=?3 ", nativeQuery = true)
    List<Votes> findByVoteBySprintIdAndParameterID(int vote_by, int parameter_id, int sprint_id);

    @Query(value = "SELECT * FROM votes_tbl where sprint_id_sprint_id=?1 and vote_by_id=?2", nativeQuery = true)
    List<Votes> findBySprintIdAndVoteBy(int sprintId, int voteBy);

    List<Votes> findBySprintId(Sprint sprintId);
}
