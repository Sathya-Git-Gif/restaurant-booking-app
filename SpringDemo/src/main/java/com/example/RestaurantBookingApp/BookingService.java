package com.example.RestaurantBookingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsForDate(String date) {
        return bookingRepository.findAllByDateTimeStartingWith(date);
    }
}
