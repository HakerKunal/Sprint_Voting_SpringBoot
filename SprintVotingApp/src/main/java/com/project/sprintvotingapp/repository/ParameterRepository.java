package com.project.sprintvotingapp.repository;

import com.project.sprintvotingapp.entity.Parameter;
import org.springframework.data.repository.CrudRepository;


public interface ParameterRepository extends CrudRepository<Parameter, Integer> {
    Parameter findByParamaterName(String parameterName);
    Parameter findByParameterId(int parameterId);
}
