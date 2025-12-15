package com.smarttravel.payment_service.controller;

import com.smarttravel.payment_service.dto.PaymentRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping
    public String makePayment(@RequestBody PaymentRequest request) {

        System.out.println("✅ Payment received for booking: " + request.getBookingId());
        System.out.println("💰 Amount: " + request.getAmount());

        return "Payment Successful for Booking ID: " + request.getBookingId();
    }
}
