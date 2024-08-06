package com.example.cabBooking.CabBookingApp.services.impl;

import com.example.cabBooking.CabBookingApp.dto.DriverDto;
import com.example.cabBooking.CabBookingApp.dto.RideDto;
import com.example.cabBooking.CabBookingApp.dto.RiderDto;
import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.Ride;
import com.example.cabBooking.CabBookingApp.entities.RideRequest;
import com.example.cabBooking.CabBookingApp.entities.enums.RideRequestStatus;
import com.example.cabBooking.CabBookingApp.entities.enums.RideStatus;
import com.example.cabBooking.CabBookingApp.exceptions.ResourceNotFoundException;
import com.example.cabBooking.CabBookingApp.repositories.DriverRepository;
import com.example.cabBooking.CabBookingApp.services.DriverService;
import com.example.cabBooking.CabBookingApp.services.RideRequestService;
import com.example.cabBooking.CabBookingApp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride request cannot be accepted, status is "+ rideRequest.getRideRequestStatus());
        }
        Driver currentDriver = getcurrentDriver();
        if(!currentDriver.isAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }
        currentDriver.setAvailable(false);
        Driver savedDriver = driverRepository.save(currentDriver);
        Ride ride = rideService.createNewRide(rideRequest,savedDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getcurrentDriver();
        if(driver != ride.getDriver()){
            throw new RuntimeException("Driver cannot start a ride as he had not accepted it earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride Status is not confirmed hence cannot be started:" + ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("OTP is not valid, otp : " + otp);
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.ONGOING);

        return modelMapper.map(savedRide, RideDto.class);

    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Double rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getcurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(()-> new ResourceNotFoundException("Driver not found by id : 2"));
    }
}
