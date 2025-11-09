package com.ExploreEase.ExploreEase.controller;
import com.ExploreEase.ExploreEase.dto.BookingResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ExploreEase.ExploreEase.dto.BookingRequest;
import com.ExploreEase.ExploreEase.dto.BookingResponse;
import com.ExploreEase.ExploreEase.entity.Booking;
import com.ExploreEase.ExploreEase.entity.User;
import com.ExploreEase.ExploreEase.repository.UserRepository;
import com.ExploreEase.ExploreEase.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    // ✅ Book a tour
    @PostMapping("/book")
    public ResponseEntity<?> bookTour(@Valid @RequestBody BookingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        Booking booking = bookingService.createBooking(
                request.getTourId(),
                user,
                request.getSeatsBooked()
        );

        return ResponseEntity.ok(booking);
    }

    // ✅ Get all bookings for a user
//    @GetMapping("/{id}")
//    public ResponseEntity<List<Booking>> getMyBookings(@PathVariable("id") Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
//
//        List<Booking> bookings = bookingService.getBookingsByUser(user);
//        return ResponseEntity.ok(bookings);
//    }
    
 // ✅ Get all bookings for the currently logged-in user
    // ✅ Get bookings of the currently logged-in user
    @GetMapping("/my")
    public ResponseEntity<List<Booking>> getMyBookingsForLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userDetails.getUsername()));

        List<Booking> bookings = bookingService.getBookingsByUser(user);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/all")
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

//
//    // ✅ Cancel a booking
//    @PostMapping("/cancel/{bookingId}")
//    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId, @RequestParam Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
//
//        Booking cancelled = bookingService.cancelBooking(bookingId, user);
//        return ResponseEntity.ok(cancelled);
//    }

    // ✅ Cancel booking for logged-in user
    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId, Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        Booking cancelled = bookingService.cancelBooking(bookingId, user);
        return ResponseEntity.ok(cancelled);
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        try {
            bookingService.cancelBookingByAdmin(id);
            return ResponseEntity.ok("Booking cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
