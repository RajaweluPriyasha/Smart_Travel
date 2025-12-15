package com.smarttravel.book_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Booking {
    private Long id;
    private String status;
    private double totalAmount;
}