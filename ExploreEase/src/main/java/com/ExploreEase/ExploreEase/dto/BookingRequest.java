package com.ExploreEase.ExploreEase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookingRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Tour ID is required")
    private Long tourId;

    @Min(value = 1, message = "At least one seat must be booked")
    private int seatsBooked;

    // Getters & Setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getTourId() {
        return tourId;
    }
    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }
    public int getSeatsBooked() {
        return seatsBooked;
    }
    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }
}
