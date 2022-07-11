package com.project.sprintvotingapp.service;

import com.project.sprintvotingapp.common.APIResponse;
import com.project.sprintvotingapp.dto.ParameterRequestDTO;
import com.project.sprintvotingapp.entity.Parameter;
import com.project.sprintvotingapp.entity.Sprint;
import com.project.sprintvotingapp.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {
    @Autowired
    private ParameterRepository parameterRepository;

    public APIResponse addParameter(ParameterRequestDTO parameterRequestDTO) {
        APIResponse apiResponse = new APIResponse();

        Parameter existParameter = parameterRepository.findByParamaterName(parameterRequestDTO.getParameterName());
        if (existParameter != null) {
            apiResponse.setMessage("Parameter Addition Unsuccessful");
            apiResponse.setError("Parameter with same name already exists");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return apiResponse;
        }


        Parameter parameter = new Parameter();
        parameter.setParamaterName(parameterRequestDTO.getParameterName());

        parameter = parameterRepository.save(parameter);
        apiResponse.setData(parameter);
        apiResponse.setMessage("Parameter addition Successful");
        return apiResponse;
    }


    public APIResponse updateParameter(ParameterRequestDTO parameterRequestDTO, int parameterId) {
        APIResponse apiResponse = new APIResponse();
        Parameter noParameter = parameterRepository.findByParameterId(parameterId);
        if (noParameter == null) {
            apiResponse.setError("parameter with given id does not exist");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Parameter Update Unsuccessful");
            return apiResponse;


        }
        Parameter existParameter = parameterRepository.findByParamaterName(parameterRequestDTO.getParameterName());
        if (existParameter != null) {
            apiResponse.setMessage("Parameter Addition Unsuccessful");
            apiResponse.setError("Parameter with same name already exists");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return apiResponse;
        }


        Parameter parameter = new Parameter();
        parameter.setParameterId(parameterId);
        parameter.setParamaterName(parameterRequestDTO.getParameterName());

        parameter = parameterRepository.save(parameter);
        apiResponse.setData(parameter);
        apiResponse.setMessage("Parameter Update Successful");
        return apiResponse;


    }

    public APIResponse deleteParameter(int parameterId){
        APIResponse apiResponse=new APIResponse();
        Parameter noParameter = parameterRepository.findByParameterId(parameterId);
        if (noParameter == null) {
            apiResponse.setError("parameter with given id does not exist");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Parameter Update Unsuccessful");
            return apiResponse;
        }
        parameterRepository.deleteById(parameterId);
        apiResponse.setMessage("Parameter Delete Successful");
        return apiResponse;

    }
    public APIResponse getAllParameter(){
        APIResponse apiResponse=new APIResponse();
        List<Parameter> list= (List<Parameter>) parameterRepository.findAll();
        apiResponse.setData(list);
        apiResponse.setMessage("Here is your Parameter List");
        return apiResponse;
    }
    public APIResponse getParameter(int parameterId){
        APIResponse apiResponse = new APIResponse();

        Parameter noParameter = parameterRepository.findByParameterId(parameterId);
        if (noParameter == null) {
            apiResponse.setError("parameter with given id does not exist");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Parameter Update Unsuccessful");
            return apiResponse;
        }
        Parameter parameter=parameterRepository.findByParameterId(parameterId);
        apiResponse.setData(parameter);
        apiResponse.setMessage("Here Your parameter");
        return apiResponse;


    }

}
