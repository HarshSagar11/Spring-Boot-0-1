package com.example.cabBooking.CabBookingApp.services;

import com.example.cabBooking.CabBookingApp.dto.WalletTransactionDto;
import com.example.cabBooking.CabBookingApp.entities.WalletTransaction;

public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
