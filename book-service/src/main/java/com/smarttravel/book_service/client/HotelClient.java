package com.smarttravel.book_service.client;

import com.smarttravel.book_service.dto.HotelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hotel", url = "http://localhost:8083")
public interface HotelClient {
    @GetMapping("/hotels/{id}")
    HotelDto getHotel(@PathVariable Long id);
}