package com.example.cabBooking.CabBookingApp.services.impl;

import com.example.cabBooking.CabBookingApp.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOsrmImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        // call the third party osrm api to fetch the distance;
        return 0;
    }
}
