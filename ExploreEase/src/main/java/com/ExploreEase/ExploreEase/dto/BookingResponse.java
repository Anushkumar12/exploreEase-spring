package com.ExploreEase.ExploreEase.dto;

import java.time.LocalDateTime;

public class BookingResponse {

    private Long id;
    private Long userId;
    private String userEmail;
    private Long tourId;
    private String tourTitle;
    private int seatsBooked;
    private String status;
    private LocalDateTime createdAt;

    // ✅ Constructor
    public BookingResponse(Long id, Long userId, String userEmail,
                           Long tourId, String tourTitle,
                           int seatsBooked, String status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.tourId = tourId;
        this.tourTitle = tourTitle;
        this.seatsBooked = seatsBooked;
        this.status = status;
        this.createdAt = createdAt;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public Long getTourId() { return tourId; }
    public void setTourId(Long tourId) { this.tourId = tourId; }

    public String getTourTitle() { return tourTitle; }
    public void setTourTitle(String tourTitle) { this.tourTitle = tourTitle; }

    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
