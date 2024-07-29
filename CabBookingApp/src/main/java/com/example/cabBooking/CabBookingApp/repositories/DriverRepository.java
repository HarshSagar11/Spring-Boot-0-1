package com.example.cabBooking.CabBookingApp.repositories;

import com.example.cabBooking.CabBookingApp.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
}
