package com.example.RestaurantBookingApp;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAllByDateTimeStartingWith(String date);
}
