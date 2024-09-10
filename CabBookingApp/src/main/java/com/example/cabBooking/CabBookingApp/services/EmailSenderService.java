package com.example.cabBooking.CabBookingApp.services;

public interface EmailSenderService {

    void SendEmail(String email, String subject, String body);
    void SendEmailsTomultiple(String toEmail[], String subject, String body);

}
