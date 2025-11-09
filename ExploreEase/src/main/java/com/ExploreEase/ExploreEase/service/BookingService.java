package com.ExploreEase.ExploreEase.service;

import java.util.List;

import com.ExploreEase.ExploreEase.dto.BookingResponse;
import com.ExploreEase.ExploreEase.entity.Booking;
import com.ExploreEase.ExploreEase.entity.User;

public interface BookingService {
    Booking createBooking(Long tourId, User user, int seats);
    List<Booking> getBookingsByUser(User user);
    Booking cancelBooking(Long bookingId, User user);
     List<BookingResponse> getAllBookings();
     void cancelBookingByAdmin(Long bookingId);
}