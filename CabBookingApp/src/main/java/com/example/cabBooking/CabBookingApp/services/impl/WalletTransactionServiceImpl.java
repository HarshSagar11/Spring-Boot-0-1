package com.example.cabBooking.CabBookingApp.services.impl;


import com.example.cabBooking.CabBookingApp.dto.WalletTransactionDto;
import com.example.cabBooking.CabBookingApp.entities.WalletTransaction;
import com.example.cabBooking.CabBookingApp.repositories.WalletTransactionRepository;
import com.example.cabBooking.CabBookingApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
