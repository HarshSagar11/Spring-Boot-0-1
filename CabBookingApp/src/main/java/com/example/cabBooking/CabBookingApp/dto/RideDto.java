package com.example.cabBooking.CabBookingApp.dto;

import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.Rider;
import com.example.cabBooking.CabBookingApp.entities.enums.PaymentMethod;
import com.example.cabBooking.CabBookingApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {
    private  Long Id;
    private Point pickupLocation;
    private  Point dropOffLocation;
    private LocalDateTime createdTime;
    private Rider rider;
    private Driver driver;
    private RideStatus rideRequestStatus;
    private PaymentMethod paymentMethod;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
