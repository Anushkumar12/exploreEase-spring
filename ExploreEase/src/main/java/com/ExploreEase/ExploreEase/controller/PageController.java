package com.ExploreEase.ExploreEase.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ExploreEase.ExploreEase.entity.Tour;
import com.ExploreEase.ExploreEase.repository.TourRepository;

@Controller
public class PageController {

    @GetMapping("/")
    public String homePage() {
        return "home"; // Thymeleaf will render templates/home.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    @Autowired
    private TourRepository tourRepository;
    @GetMapping("/tours")
    public String showTours(Model model) {
        List<Tour> tours = tourRepository.findAll();
        model.addAttribute("tours", tours);
        return "list"; // this corresponds to templates/tour-list.html
    }
    
    @GetMapping("/add-tour")
    public String showAddTourPage() {
        return "add_tour";
    }
    
    @GetMapping("/tour-details")
    public String showtours() {
        return "tour-details";
    }
    
    @GetMapping("/book-tour")
    public String showBookingPage() {
        return "book-tour";
    }
    
    @GetMapping("/book-tour/{id}")
    public String bookTourPage(@PathVariable Long id, Model model) {
        // Fetch the tour from DB
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found with id: " + id));

        // Add tour data to model
        model.addAttribute("tour", tour);
        return "book-tour";
    }
    
    @GetMapping("/booking-success")
    public String bookingSuccessPage() {
        return "booking-success";
    }

    @GetMapping("/my-bookings")
    public String showMyBookings() {
        return "my-bookings"; // templates/my-bookings.html
    }
   

    @GetMapping("/admin-dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard(Authentication auth, Model model) {
        String adminEmail = auth != null ? auth.getName() : "Admin";
        model.addAttribute("adminEmail", adminEmail);
        return "admin-dashboard";
    }

	        @GetMapping("/manage-bookings")
	        public String manageBookings() {
	            return "manage-bookings";
	        }
}

