package com.example.cabBooking.CabBookingApp.services.impl;

import com.example.cabBooking.CabBookingApp.dto.DriverDto;
import com.example.cabBooking.CabBookingApp.dto.SignupDto;
import com.example.cabBooking.CabBookingApp.dto.UserDto;
import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.Rider;
import com.example.cabBooking.CabBookingApp.entities.User;
import com.example.cabBooking.CabBookingApp.entities.enums.Role;
import com.example.cabBooking.CabBookingApp.exceptions.ResourceNotFoundException;
import com.example.cabBooking.CabBookingApp.exceptions.RuntimeConflictException;
import com.example.cabBooking.CabBookingApp.repositories.UserRepository;
import com.example.cabBooking.CabBookingApp.services.AuthService;
import com.example.cabBooking.CabBookingApp.services.DriverService;
import com.example.cabBooking.CabBookingApp.services.RiderService;
import com.example.cabBooking.CabBookingApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.example.cabBooking.CabBookingApp.entities.enums.Role.DRIVER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;

    @Override
    public String login(String email, String Password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User newUser =  userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(newUser != null){
            throw new RuntimeConflictException("User with this email already exist, please login.");
        }
        User user =  modelMapper.map(signupDto, User.class);
        user.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(user);

        // create user related entities
        Rider rider = riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onBoardDriver(Long userId,String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id : "+ userId));
        if(user.getRoles().contains(DRIVER))
            throw new RuntimeException("User with id : " + userId +  "is Already a driver.");

        Driver createdDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createdDriver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }
}
