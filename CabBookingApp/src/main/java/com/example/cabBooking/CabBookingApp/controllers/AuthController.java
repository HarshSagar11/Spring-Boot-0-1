package com.example.cabBooking.CabBookingApp.controllers;

import com.example.cabBooking.CabBookingApp.dto.SignupDto;
import com.example.cabBooking.CabBookingApp.dto.UserDto;
import com.example.cabBooking.CabBookingApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/signUp")
    UserDto signUp(@RequestBody SignupDto signupDto){
        return authService.signup(signupDto);
    }

}
