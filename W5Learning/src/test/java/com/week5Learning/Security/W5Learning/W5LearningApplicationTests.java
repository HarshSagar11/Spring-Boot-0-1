package com.week5Learning.Security.W5Learning;

import com.week5Learning.Security.W5Learning.entities.UserEntity;
import com.week5Learning.Security.W5Learning.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class W5LearningApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		UserEntity user  = new UserEntity(4L,"harsh@gmail.com","1234");

		String token = jwtService.generateToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);
	}

}
