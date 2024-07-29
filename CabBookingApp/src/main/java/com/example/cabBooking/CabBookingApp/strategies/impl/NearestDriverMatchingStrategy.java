package com.example.cabBooking.CabBookingApp.strategies.impl;

import com.example.cabBooking.CabBookingApp.dto.RideRequestDto;
import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
