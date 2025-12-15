package com.smarttravel.book_service.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long userId;
    private Long flightId;
    private Long hotelId;
    private String travelDate;
}