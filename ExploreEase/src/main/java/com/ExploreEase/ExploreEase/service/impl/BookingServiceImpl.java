package com.ExploreEase.ExploreEase.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ExploreEase.ExploreEase.dto.BookingResponse;
import com.ExploreEase.ExploreEase.entity.Booking;
import com.ExploreEase.ExploreEase.entity.Tour;
import com.ExploreEase.ExploreEase.entity.User;
import com.ExploreEase.ExploreEase.repository.BookingRepository;
import com.ExploreEase.ExploreEase.repository.TourRepository;
import com.ExploreEase.ExploreEase.service.BookingService;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TourRepository tourRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, TourRepository tourRepository) {
        this.bookingRepository = bookingRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    @Transactional
    public Booking createBooking(Long tourId, User user, int seats) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found with id: " + tourId));

        if (tour.getSeatsAvailable() < seats) {
            throw new RuntimeException("Not enough seats available. Remaining: " + tour.getSeatsAvailable());
        }

        // Decrement seats and save tour
        tour.setSeatsAvailable(tour.getSeatsAvailable() - seats);
        tourRepository.save(tour);

        Booking booking = Booking.builder()
                .tour(tour)
                .user(user)
                .seatsBooked(seats)
                .status("CONFIRMED")
                .createdAt(LocalDateTime.now())
                .build();

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }
   
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
            .map(b -> new BookingResponse(
                b.getId(),
                b.getUser().getId(),
                b.getUser().getEmail(),
                b.getTour().getId(),
                b.getTour().getTitle(),
                b.getSeatsBooked(),
                b.getStatus(),
                b.getCreatedAt()
            ))
            .toList();
    }

    
    @Override
    @Transactional
    public Booking cancelBooking(Long bookingId, User user) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized cancellation attempt");
        }

        if (booking.getStatus().equals("CANCELLED")) {
            throw new RuntimeException("Booking already cancelled");
        }

        booking.setStatus("CANCELLED");

        // Re-add seats to the tour
        Tour tour = booking.getTour();
        tour.setSeatsAvailable(tour.getSeatsAvailable() + booking.getSeatsBooked());
        tourRepository.save(tour);

        return bookingRepository.save(booking);
    }
    @Transactional
    public void cancelBookingByAdmin(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus().equals("CANCELLED"))
            throw new RuntimeException("Booking already cancelled");

        booking.setStatus("CANCELLED");

        Tour tour = booking.getTour();
        tour.setSeatsAvailable(tour.getSeatsAvailable() + booking.getSeatsBooked());
        tourRepository.save(tour);
        bookingRepository.save(booking);
    }

}