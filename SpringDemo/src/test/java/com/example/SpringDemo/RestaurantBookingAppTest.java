package com.example.SpringDemo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.annotation.PostConstruct;
import static org.junit.jupiter.api.Assertions.*;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RestaurantBookingAppTest {

    private static final OkHttpClient client = new OkHttpClient();

    @PostConstruct
    public void init() {
        // Initialization code
    }

    @Test
    void testCreateBooking() throws IOException {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                "{\"customerName\":\"Sathya\",\"tableSize\":4,\"dateTime\":\"2024-11-05T19:00:00\"}"
        );
        Request request = new Request.Builder()
                .url("http://localhost:8080/booking")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        assertTrue(responseBody.contains("Sathya"));
    }

    @Test
    void testGetBookingsForDate() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/bookings?date=2024-11-05")
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        assertTrue(responseBody.contains("Sathya"));
    }
}
