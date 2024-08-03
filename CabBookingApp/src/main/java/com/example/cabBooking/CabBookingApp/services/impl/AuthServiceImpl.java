package com.example.cabBooking.CabBookingApp.services.impl;

import com.example.cabBooking.CabBookingApp.dto.DriverDto;
import com.example.cabBooking.CabBookingApp.dto.SignupDto;
import com.example.cabBooking.CabBookingApp.dto.UserDto;
import com.example.cabBooking.CabBookingApp.entities.Rider;
import com.example.cabBooking.CabBookingApp.entities.User;
import com.example.cabBooking.CabBookingApp.entities.enums.Role;
import com.example.cabBooking.CabBookingApp.exceptions.RuntimeConflictException;
import com.example.cabBooking.CabBookingApp.repositories.UserRepository;
import com.example.cabBooking.CabBookingApp.services.AuthService;
import com.example.cabBooking.CabBookingApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String Password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        User newUser =  userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(newUser != null){
            throw new RuntimeConflictException("User with this email already exist, please login.");
        }
        User user =  modelMapper.map(signupDto, User.class);
        user.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(user);

        // create user related entities
        riderService.createNewRider(savedUser);
        // TODO add wallet related service

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onBoardDriver(Long userId) {
        return null;
    }
}
