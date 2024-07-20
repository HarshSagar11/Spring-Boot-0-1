package com.week3.JPATutorial.Week3;

import com.week3.JPATutorial.Week3.entities.ProductEntity;
import com.week3.JPATutorial.Week3.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class Week3ApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle234")
				.title("Nestle Chocolate")
				.price (BigDecimal.valueOf(123.45))
				.quantity (12)
				.build();
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}
	@Test
	void getRepository() {
		List<ProductEntity> entities = productRepository.findByTitle("Pepsi");
		System.out.println(entities);
	}

}
