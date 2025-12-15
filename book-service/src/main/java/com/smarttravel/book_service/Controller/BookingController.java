package com.smarttravel.book_service.Controller;

import com.smarttravel.book_service.client.FlightClient;
import com.smarttravel.book_service.client.HotelClient;
import com.smarttravel.book_service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private FlightClient flightClient;

    @Autowired
    private HotelClient hotelClient;

    private static Long bookingId = 1L;

    @PostMapping
    public String createBooking(@RequestBody BookingRequest request) {

        // 1️⃣ Validate User (FIXED PORT)
        String user = webClientBuilder.build()
                .get()
                .uri("http://localhost:8086/users/" + request.getUserId())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // 2️⃣ Flight
        FlightDto flight = flightClient.getFlight(request.getFlightId());

        // 3️⃣ Hotel
        HotelDto hotel = hotelClient.getHotel(request.getHotelId());

        double total = flight.getPrice() + hotel.getPrice();

        // 4️⃣ Payment
        PaymentRequest paymentRequest =
                new PaymentRequest(bookingId, total);

        webClientBuilder.build()
                .post()
                .uri("http://localhost:8085/payments")
                .bodyValue(paymentRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // 5️⃣ Notification
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8084/notifications")
                .bodyValue("Booking Confirmed")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        bookingId++;
        return "{\"id\":" + (bookingId - 1) +
                ",\"status\":\"CONFIRMED\",\"totalAmount\":" + total + "}";
    }
}
