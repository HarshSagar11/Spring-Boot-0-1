package com.example.cabBooking.CabBookingApp.dto;

import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.enums.PaymentMethod;
import com.example.cabBooking.CabBookingApp.entities.enums.RideStatus;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data
public class RideDto {
    private  Long Id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private RideStatus rideStatus;
    private PaymentMethod paymentMethod;
    private String otp;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
