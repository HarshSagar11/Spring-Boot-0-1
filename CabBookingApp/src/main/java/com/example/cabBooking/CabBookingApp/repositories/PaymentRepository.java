package com.example.cabBooking.CabBookingApp.repositories;

import com.example.cabBooking.CabBookingApp.entities.Payment;
import com.example.cabBooking.CabBookingApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
