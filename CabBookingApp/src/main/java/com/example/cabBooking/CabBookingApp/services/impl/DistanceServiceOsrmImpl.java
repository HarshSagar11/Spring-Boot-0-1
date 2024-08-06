package com.example.cabBooking.CabBookingApp.services.impl;

import com.example.cabBooking.CabBookingApp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOsrmImpl implements DistanceService {

    private static final  String OSRM_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {
        // call the third party osrm api to fetch the distance;
        String URI = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
        try{
            OSRMResponseDto responseDto = RestClient.builder()
                    .baseUrl(OSRM_BASE_URL)
                    .build()
                    .get()
                    .uri(URI)
                    .retrieve()
                    .body(OSRMResponseDto.class);
            return responseDto.getRoutes().get(0).getDistance() / 1000.0;
        }
        catch (Exception e){
            throw new RuntimeException("Error getting data from OSRM"+e.getMessage() + " URL = " + OSRM_BASE_URL + URI);
        }

    }
}

@Data
class OSRMResponseDto{
    private List<OSRMRoutes> routes;
}
@Data
class OSRMRoutes{
    private Double distance;
}