package com.example.cabBooking.CabBookingApp;

import com.example.cabBooking.CabBookingApp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CabBookingAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.SendEmail(
				"sijix15401@konetas.com",
				"this is test mail",
				"body of my mail"
		);
	}

	@Test
	void sendMultipleMail() {
		String emails[] = {"sijix15401@konetas.com","harshsagarkumar1111@gmail.com"};
		emailSenderService.SendEmailsTomultiple(
				emails,
				"this is test multiple mail",
				"body of my multiple mail"
		);
	}

}
