package week7.testingApp.TestingApp;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest // this annotation run whole spring application
@Slf4j
class TestingAppApplicationTests {

//	@BeforeAll // run before all test case (method must be static)
// 	@AfterAll // run after all test case (method must be static)
//	Assertions.assertEquals compare 2 things, 2 inputs or 1 expected value and 1 return value of fn

	@BeforeEach //before every test case
	void setup(){
		log.info("Starting the method, setting up config");
	}

	@AfterEach //before every test case
	void finishingUp(){
		log.info("Ending the method");
	}

	@Test // mark a method as test method
	void testNumber1() {
		assertThat(1).isEqualTo(1).isCloseTo(2, Offset.offset(1));
		log.info("test 1 is run");
	}

	@Test // mark a method as test method
	void testNumber2() {
		log.info("test 2 is run");
	}

}
