package com.project.sprintvotingapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "parameter_tbl")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parameterId;

    public Parameter() {
    }

    private String paramaterName;

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    public String getParamaterName() {
        return paramaterName;
    }

    public void setParamaterName(String paramaterName) {
        this.paramaterName = paramaterName;
    }
}
