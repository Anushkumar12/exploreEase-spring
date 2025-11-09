package com.ExploreEase.ExploreEase.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column(nullable = false)
    private int seatsBooked;

    @Column(nullable = false)
    private String status; // e.g., CONFIRMED, CANCELLED

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // ✅ Default Constructor
    public Booking() {}

    // ✅ Parameterized Constructor
    public Booking(Long id, User user, Tour tour, int seatsBooked, String status, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.tour = tour;
        this.seatsBooked = seatsBooked;
        this.status = status;
        this.createdAt = createdAt;
    }

    // ✅ Manual Builder (matching actual fields)
    public static class Builder {
        private Long id;
        private User user;
        private Tour tour;
        private int seatsBooked;
        private String status;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder tour(Tour tour) { this.tour = tour; return this; }
        public Builder seatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Booking build() {
            return new Booking(id, user, tour, seatsBooked, status, createdAt);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Tour getTour() { return tour; }
    public void setTour(Tour tour) { this.tour = tour; }

    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
