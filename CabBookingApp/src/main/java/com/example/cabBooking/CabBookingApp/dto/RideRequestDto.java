package com.example.cabBooking.CabBookingApp.dto;

import com.example.cabBooking.CabBookingApp.entities.enums.PaymentMethod;
import com.example.cabBooking.CabBookingApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    private  Long Id;
    private PointDto pickupLocation;
    private  PointDto dropOffLocation;
    private LocalDateTime requestedTime;
    private RiderDto rider;
    private double fare;
    private RideRequestStatus rideRequestStatus;
    private PaymentMethod paymentMethod;
}
