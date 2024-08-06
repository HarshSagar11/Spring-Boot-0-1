package com.example.cabBooking.CabBookingApp.strategies;

import com.example.cabBooking.CabBookingApp.entities.RideRequest;

public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
