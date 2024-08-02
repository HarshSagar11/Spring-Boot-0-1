package com.example.week4.learningW4.clients.impl;

import com.example.week4.learningW4.advices.ApiResponse;
import com.example.week4.learningW4.clients.EmployeeClient;
import com.example.week4.learningW4.dto.EmployeesDTO;
import com.example.week4.learningW4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeesDTO> getAllEmployees() {
        try{
            ApiResponse<List<EmployeesDTO>> employeesDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){

                    });
            return employeesDTOList.getData();
        }
        catch (Exception e){
            throw new RuntimeException("some error in rest client");
        }
    }

    @Override
    public EmployeesDTO getEmployeeById(Long employeeId) {
        try{
            ApiResponse<EmployeesDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<ApiResponse<EmployeesDTO>>() {
                    });
            return employeeResponse.getData();
        }
        catch(Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public EmployeesDTO createNewEmployee(EmployeesDTO employee) {
        try{
            ApiResponse<EmployeesDTO> employeesDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employee)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError ,(req,resp)->{
                        System.out.println(new String(resp.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the Employee");
                    })
                    .body(new ParameterizedTypeReference<>(){});
            return employeesDTOApiResponse.getData();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
