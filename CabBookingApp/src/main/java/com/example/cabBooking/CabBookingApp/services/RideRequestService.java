package com.example.cabBooking.CabBookingApp.services;

import com.example.cabBooking.CabBookingApp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
