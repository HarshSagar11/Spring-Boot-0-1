package com.example.cabBooking.CabBookingApp.strategies.impl;

import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.RideRequest;
import com.example.cabBooking.CabBookingApp.strategies.DriverMatchingStrategy;

import java.util.List;

public class HighestRatedDriverMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return List.of();
    }
}
