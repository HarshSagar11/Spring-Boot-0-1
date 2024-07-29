package com.example.cabBooking.CabBookingApp.strategies;

import com.example.cabBooking.CabBookingApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}
