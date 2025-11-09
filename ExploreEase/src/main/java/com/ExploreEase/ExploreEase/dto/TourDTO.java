package com.ExploreEase.ExploreEase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TourDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String location;

    @Min(1)
    private double price;

    private int durationDays;
    private int seatsAvailable;
    private String imageUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDurationDays() {
		return durationDays;
	}
	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public TourDTO(Long id, @NotBlank String title, @NotBlank String description, @NotBlank String location,
			@Min(1) double price, int durationDays, int seatsAvailable, String imageUrl) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.price = price;
		this.durationDays = durationDays;
		this.seatsAvailable = seatsAvailable;
		this.imageUrl = imageUrl;
	}
	public TourDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters & setters
    
}