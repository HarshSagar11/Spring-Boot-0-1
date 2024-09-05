package com.example.cabBooking.CabBookingApp.strategies.impl;

import com.example.cabBooking.CabBookingApp.entities.Driver;
import com.example.cabBooking.CabBookingApp.entities.Payment;
import com.example.cabBooking.CabBookingApp.entities.enums.PaymentStatus;
import com.example.cabBooking.CabBookingApp.entities.enums.TransactionMethod;
import com.example.cabBooking.CabBookingApp.repositories.PaymentRepository;
import com.example.cabBooking.CabBookingApp.services.PaymentService;
import com.example.cabBooking.CabBookingApp.services.WalletService;
import com.example.cabBooking.CabBookingApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null ,
                payment.getRide() , TransactionMethod.RIDE );

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
