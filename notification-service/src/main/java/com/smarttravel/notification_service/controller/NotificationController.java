package com.smarttravel.notification_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    // ADD THIS for testing via browser
    @GetMapping
    public String test() {
        return "Notification Service is running! Use POST method to send notification.";
    }

    @PostMapping
    public String sendNotification(@RequestBody String message) {
        System.out.println("Notification Received: " + message);
        return "Notification Sent: " + message;
    }
}