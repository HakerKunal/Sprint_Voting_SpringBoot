package com.project.sprintvotingapp.dto;

import com.project.sprintvotingapp.entity.User;

import java.util.HashMap;
import java.util.List;

public class VoteRequestDTO {
    private HashMap parameter_list;

    public void setParameter_list(HashMap parameter_list) {
        this.parameter_list = parameter_list;
    }

    public HashMap getParameter_list() {
        return parameter_list;
    }
}
