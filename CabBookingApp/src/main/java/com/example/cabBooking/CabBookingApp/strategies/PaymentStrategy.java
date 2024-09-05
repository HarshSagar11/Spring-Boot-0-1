package com.example.cabBooking.CabBookingApp.strategies;

import com.example.cabBooking.CabBookingApp.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}
