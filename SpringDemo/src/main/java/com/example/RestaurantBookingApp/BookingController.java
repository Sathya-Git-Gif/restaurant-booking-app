package com.example.RestaurantBookingApp;

import io.muserver.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void startServer() {
        MuServer server = MuServerBuilder.muServer()
                .withHttpPort(8080)
                .addHandler(Method.POST, "/booking", this::createBooking)
                .addHandler(Method.GET, "/bookings", this::getBookings)
                .start();

        System.out.println("Server started at " + server.uri());
    }

    private void createBooking(MuRequest req, MuResponse resp, Map<String, String> pathParams) throws Exception {
        Optional<InputStream> optionalInputStream = req.inputStream();

        if (optionalInputStream.isPresent()) {
            InputStream inputStream = optionalInputStream.get();
            Booking booking = objectMapper.readValue(inputStream, Booking.class);
            bookingService.addBooking(booking);
            resp.write("Booking created for " + booking.getCustomerName());
        } else {
            resp.write("Booking not created -Status code is : " + resp.status());
        }

        //Booking booking = objectMapper.readValue(req.inputStream(), Booking.class );

    }

    private void getBookings(MuRequest req, MuResponse resp, Map<String, String> pathParams) throws Exception {
        String date = req.query().get("date");
        List<Booking> filteredBookings = bookingService.getBookingsForDate(date);
        resp.contentType(ContentTypes.APPLICATION_JSON);
        resp.write(objectMapper.writeValueAsString(filteredBookings));
    }
}

