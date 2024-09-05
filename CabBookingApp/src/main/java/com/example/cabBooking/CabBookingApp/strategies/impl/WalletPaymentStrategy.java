package com.example.cabBooking.CabBookingApp.strategies.impl;

import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.Payment;
import com.example.cabBooking.CabBookingApp.entities.Rider;
import com.example.cabBooking.CabBookingApp.entities.enums.PaymentStatus;
import com.example.cabBooking.CabBookingApp.entities.enums.TransactionMethod;
import com.example.cabBooking.CabBookingApp.repositories.PaymentRepository;
import com.example.cabBooking.CabBookingApp.services.PaymentService;
import com.example.cabBooking.CabBookingApp.services.WalletService;
import com.example.cabBooking.CabBookingApp.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),payment.getAmount(), null, payment.getRide(),
                TransactionMethod.RIDE);
        double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);
        walletService.addMoneyTowallet(driver.getUser(), driversCut, null ,
                payment.getRide(),TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
