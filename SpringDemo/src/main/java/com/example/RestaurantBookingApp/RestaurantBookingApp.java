package com.example.RestaurantBookingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantBookingApp {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantBookingApp.class, args);
        BookingController controller = new BookingController();
        controller.startServer();
    }
}