package com.example.cabBooking.CabBookingApp.services.impl;

import com.example.cabBooking.CabBookingApp.dto.DriverDto;
import com.example.cabBooking.CabBookingApp.dto.RiderDto;
import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.Rating;
import com.example.cabBooking.CabBookingApp.entities.Ride;
import com.example.cabBooking.CabBookingApp.entities.Rider;
import com.example.cabBooking.CabBookingApp.exceptions.ResourceNotFoundException;
import com.example.cabBooking.CabBookingApp.exceptions.RuntimeConflictException;
import com.example.cabBooking.CabBookingApp.repositories.DriverRepository;
import com.example.cabBooking.CabBookingApp.repositories.RatingRepository;
import com.example.cabBooking.CabBookingApp.repositories.RiderRepository;
import com.example.cabBooking.CabBookingApp.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("Rating not found for the ride with id: "+ ride.getId()));
        ratingObj.setDriverRating(rating);
        if(ratingObj.getDriverRating() != null)
            throw new RuntimeConflictException("Driver has already rated, can't rate again");

        ratingRepository.save(ratingObj);
        Double netRating = ratingRepository.findByDriver(driver)
                .stream().mapToDouble(Rating::getDriverRating)
                .average().orElse(0.0);
        driver.setRating(netRating);
        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("Rating not found for the ride with id: "+ ride.getId()));
        ratingObj.setRiderRating(rating);
        if(ratingObj.getRiderRating() != null)
            throw new RuntimeConflictException("Rider has already rated, can't rate again");

        ratingRepository.save(ratingObj);
        Double netRating = ratingRepository.findByRider(rider)
                .stream().mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);
        rider.setRating(netRating);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
