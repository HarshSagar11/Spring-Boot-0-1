package com.example.week4.learningW4;

import com.example.week4.learningW4.advices.ApiResponse;
import com.example.week4.learningW4.clients.EmployeeClient;
import com.example.week4.learningW4.dto.EmployeesDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class LearningW4ApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void getAllEmployees(){
		List<EmployeesDTO> employeesDTOList = employeeClient.getAllEmployees();
		System.out.println(employeesDTOList);
	}
	@Test
	void getEmployeeById(){
		EmployeesDTO employeesDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeesDTO);
	}

	@Test
	void createNewEmployee(){
		EmployeesDTO employeesDTO = new EmployeesDTO(null,"harsh","h@gmail.com",20,
				"manager", LocalDate.of(2020,10,1),true);
		EmployeesDTO savedEmp = employeeClient.createNewEmployee(employeesDTO);
		System.out.println(savedEmp);
	}

}
