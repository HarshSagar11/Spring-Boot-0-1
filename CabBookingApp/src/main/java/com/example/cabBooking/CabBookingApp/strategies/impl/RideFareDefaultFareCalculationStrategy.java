package com.example.cabBooking.CabBookingApp.strategies.impl;

import com.example.cabBooking.CabBookingApp.entities.RideRequest;
import com.example.cabBooking.CabBookingApp.services.DistanceService;
import com.example.cabBooking.CabBookingApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(
                rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER;
    }
}
