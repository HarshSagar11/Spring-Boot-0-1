package com.example.cabBooking.CabBookingApp.services;

import com.example.cabBooking.CabBookingApp.entities.Ride;
import com.example.cabBooking.CabBookingApp.entities.User;
import com.example.cabBooking.CabBookingApp.entities.Wallet;
import com.example.cabBooking.CabBookingApp.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyTowallet(User user, Double amount, String transactionId, Ride ride,
                            TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride,
                                 TransactionMethod transactionMethod);
    void withdrawAllMyMoneyFromWallet();
    Wallet findWalletById(Long walletId);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
}
