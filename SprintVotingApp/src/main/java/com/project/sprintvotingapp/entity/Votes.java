package com.project.sprintvotingapp.entity;

import javax.persistence.*;

@Entity
@Table(name="votes_tbl")
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sprint_id_sprint_id")
    private Sprint sprintId;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameter_id_parameter_id")
    private Parameter parameterId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vote_by_id")
    private User  voteBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vote_to_id")
    private User voteTo;

    public Votes() {
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteBy(User voteBy) {
        this.voteBy = voteBy;
    }

    public void setVoteTo(User voteTo) {
        this.voteTo = voteTo;
    }

    public void setParameterId(Parameter parameterId) {
        this.parameterId = parameterId;
    }

    public User getVoteBy() {
        return voteBy;
    }

    public User getVoteTo() {
        return voteTo;
    }

    public Parameter getParameterId() {
        return parameterId;
    }

    public void setSprintId(Sprint sprintId) {
        this.sprintId = sprintId;
    }

    public Sprint getSprintId() {
        return sprintId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }
}
