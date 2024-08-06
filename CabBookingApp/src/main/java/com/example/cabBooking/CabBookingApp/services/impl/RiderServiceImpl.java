package com.example.cabBooking.CabBookingApp.services.impl;

import com.example.cabBooking.CabBookingApp.dto.DriverDto;
import com.example.cabBooking.CabBookingApp.dto.RideDto;
import com.example.cabBooking.CabBookingApp.dto.RideRequestDto;
import com.example.cabBooking.CabBookingApp.dto.RiderDto;
import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.RideRequest;
import com.example.cabBooking.CabBookingApp.entities.Rider;
import com.example.cabBooking.CabBookingApp.entities.User;
import com.example.cabBooking.CabBookingApp.entities.enums.RideRequestStatus;
import com.example.cabBooking.CabBookingApp.exceptions.ResourceNotFoundException;
import com.example.cabBooking.CabBookingApp.repositories.RideRequestRepository;
import com.example.cabBooking.CabBookingApp.repositories.RiderRepository;
import com.example.cabBooking.CabBookingApp.services.RiderService;
import com.example.cabBooking.CabBookingApp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private static final Logger log = LoggerFactory.getLogger(RiderServiceImpl.class);
    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        rideRequest.setRider(rider);

        double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers =  rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
//        TODO : SEND notification to all the drivers

        return modelMapper.map(savedRideRequest, RideRequestDto.class);

    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Double rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        // TODO : implement Spring Security
        return riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException(
                "Rider not found with the id "  + 1
        ));
    }


}
