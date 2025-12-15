package com.smarttravel.hotel_service.controller;

import com.smarttravel.hotel_service.dto.HotelDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @GetMapping("/{id}")
    public HotelDto getHotel(@PathVariable Long id) {
        return new HotelDto(id, true, 8000);
    }
}