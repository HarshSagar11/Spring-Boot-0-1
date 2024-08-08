package com.example.week4.learningW4.clients.impl;

import com.example.week4.learningW4.advices.ApiResponse;
import com.example.week4.learningW4.clients.EmployeeClient;
import com.example.week4.learningW4.dto.EmployeesDTO;
import com.example.week4.learningW4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;
    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeesDTO> getAllEmployees() {

//        log.error("error log");
//        log.warn("warn log");
//        log.info("info log");
//        // below 2 log can't be seen as it is it needs to be enabled by log levels
//        log.debug("debug log");
//        log.trace("trace log");
    log.trace("Trying to retrieve all the employee from getAllEmployee");
        try{
            ApiResponse<List<EmployeesDTO>> employeesDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError ,(req,resp)->{
                        log.error(new String(resp.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the Employee");
                    })
                    .body(new ParameterizedTypeReference<>(){
                    });
            log.info("successfully retrieved all the employee");
            log.trace("Retrieved employee list in get all Employee : {}", employeesDTOList.getData());
            return employeesDTOList.getData();
        }
        catch (Exception e){
            log.error("Exception occurred in getAllEmployee",e); // this will print all the stack trace
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
                        log.error(new String(resp.getBody().readAllBytes()));
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
