package com.example.cabBooking.CabBookingApp.services;

import com.example.cabBooking.CabBookingApp.dto.DriverDto;
import com.example.cabBooking.CabBookingApp.dto.RiderDto;
import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.Ride;
import com.example.cabBooking.CabBookingApp.entities.Rider;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);
}
