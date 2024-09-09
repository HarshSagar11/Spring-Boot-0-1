package com.example.cabBooking.CabBookingApp.services;

import com.example.cabBooking.CabBookingApp.dto.DriverDto;
import com.example.cabBooking.CabBookingApp.dto.SignupDto;
import com.example.cabBooking.CabBookingApp.dto.UserDto;

public interface AuthService {
    String[] login(String email, String Password);
    UserDto signup(SignupDto signupDto);
    DriverDto onBoardDriver(Long userId,String vehicleId);

    String refreshToken(String refreshToken);
}
