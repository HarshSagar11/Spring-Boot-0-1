package com.example.cabBooking.CabBookingApp.strategies;

import com.example.cabBooking.CabBookingApp.strategies.impl.HighestRatedDriverMatchingStrategy;
import com.example.cabBooking.CabBookingApp.strategies.impl.NearestDriverMatchingStrategy;
import com.example.cabBooking.CabBookingApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.example.cabBooking.CabBookingApp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final HighestRatedDriverMatchingStrategy highestRatedDriverMatchingStrategy;
    private final NearestDriverMatchingStrategy nearestDriverMatchingStrategy;
    private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if(riderRating >= 4.0){
            return highestRatedDriverMatchingStrategy;
        }
        else{
            return  nearestDriverMatchingStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
//        peak hour 6pm to 9pm
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return surgePricingFareCalculationStrategy;
        }
        else{
            return defaultFareCalculationStrategy;
        }

    }

}
