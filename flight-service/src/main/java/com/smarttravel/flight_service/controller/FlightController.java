package com.smarttravel.flight_service.controller;

import com.smarttravel.flight_service.dto.FlightDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @GetMapping("/{id}")
    public FlightDto getFlight(@PathVariable Long id) {
        return new FlightDto(id, true, 15000);
    }
}
