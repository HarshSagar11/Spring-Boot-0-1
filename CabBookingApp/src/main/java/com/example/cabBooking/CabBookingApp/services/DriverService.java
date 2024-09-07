package com.example.cabBooking.CabBookingApp.services;

import com.example.cabBooking.CabBookingApp.dto.DriverDto;
import com.example.cabBooking.CabBookingApp.dto.RideDto;
import com.example.cabBooking.CabBookingApp.dto.RiderDto;
import com.example.cabBooking.CabBookingApp.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
    RideDto acceptRide(Long rideRequestId);
    RideDto cancelRide(Long rideId);
    RideDto startRide(Long rideId, String otp);
    RideDto endRide(Long rideId);
    RiderDto rateRider(Long rideId,Integer rating);
    DriverDto getMyProfile();
    Page<RideDto> getAllMyRides(PageRequest pageRequest);
    Driver getcurrentDriver();
    Driver updateDriverAvailability(Driver driver, boolean available);
    Driver createNewDriver(Driver driver);
}
