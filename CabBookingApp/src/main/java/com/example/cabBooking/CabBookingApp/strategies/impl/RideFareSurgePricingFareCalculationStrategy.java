package com.example.cabBooking.CabBookingApp.strategies.impl;

import com.example.cabBooking.CabBookingApp.entities.RideRequest;
import com.example.cabBooking.CabBookingApp.services.DistanceService;
import com.example.cabBooking.CabBookingApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2.0;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(
                rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
