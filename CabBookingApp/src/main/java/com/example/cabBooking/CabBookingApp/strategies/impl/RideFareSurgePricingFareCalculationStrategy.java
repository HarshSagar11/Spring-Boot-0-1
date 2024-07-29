package com.example.cabBooking.CabBookingApp.strategies.impl;

import com.example.cabBooking.CabBookingApp.dto.RideRequestDto;
import com.example.cabBooking.CabBookingApp.strategies.RideFareCalculationStrategy;

public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
