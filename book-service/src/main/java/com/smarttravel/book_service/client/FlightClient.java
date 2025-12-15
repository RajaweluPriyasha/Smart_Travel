package com.smarttravel.book_service.client;

import com.smarttravel.book_service.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "flight", url = "http://localhost:8082")
public interface FlightClient {
    @GetMapping("/flights/{id}")
    FlightDto getFlight(@PathVariable Long id);
}