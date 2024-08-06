package com.example.cabBooking.CabBookingApp.services.impl;

import com.example.cabBooking.CabBookingApp.entities.RideRequest;
import com.example.cabBooking.CabBookingApp.exceptions.ResourceNotFoundException;
import com.example.cabBooking.CabBookingApp.repositories.RideRequestRepository;
import com.example.cabBooking.CabBookingApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(()->new ResourceNotFoundException("Ride request not found with id: " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(()-> new ResourceNotFoundException("ride request not found with id: "+ rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
