package com.example.cabBooking.CabBookingApp.strategies;

import com.example.cabBooking.CabBookingApp.entities.enums.PaymentMethod;
import com.example.cabBooking.CabBookingApp.strategies.impl.CashPaymentStrategy;
import com.example.cabBooking.CabBookingApp.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;
    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
