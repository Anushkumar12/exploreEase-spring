package com.ExploreEase.ExploreEase.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int durationDays;

    @Column(nullable = false)
    private int seatsAvailable;

    private String imageUrl;

    // ✅ Default constructor
    public Tour() {}

    // ✅ Parameterized constructor
    public Tour(Long id, String title, String description, String location,
                double price, int durationDays, int seatsAvailable, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.price = price;
        this.durationDays = durationDays;
        this.seatsAvailable = seatsAvailable;
        this.imageUrl = imageUrl;
    }

    // ✅ Manual Builder class (matches actual fields)
    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private String location;
        private double price;
        private int durationDays;
        private int seatsAvailable;
        private String imageUrl;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder location(String location) { this.location = location; return this; }
        public Builder price(double price) { this.price = price; return this; }
        public Builder durationDays(int durationDays) { this.durationDays = durationDays; return this; }
        public Builder seatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; return this; }
        public Builder imageUrl(String imageUrl) { this.imageUrl = imageUrl; return this; }

        public Tour build() {
            return new Tour(id, title, description, location, price, durationDays, seatsAvailable, imageUrl);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getDurationDays() { return durationDays; }
    public void setDurationDays(int durationDays) { this.durationDays = durationDays; }

    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
