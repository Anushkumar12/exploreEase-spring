package com.ExploreEase.ExploreEase.controller;

import com.ExploreEase.ExploreEase.dto.TourDTO;
import com.ExploreEase.ExploreEase.service.TourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tours")
public class AdminTourController {

    private final TourService tourService;

    public AdminTourController(TourService tourService) {
        this.tourService = tourService;
    }

    // 🟢 List all tours for admin
    @GetMapping
    public String listTours(Model model) {
        model.addAttribute("tours", tourService.getAllTours());
        return "admin/manage-tours"; // → templates/admin/manage-tours.html
    }

    // 🟡 Show update form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("tour", tourService.getTourById(id));
        return "admin/update-tour"; // → templates/admin/update-tour.html
    }

    // 🟢 Handle update
    @PostMapping("/edit/{id}")
    public String updateTour(@PathVariable Long id, @ModelAttribute("tour") TourDTO dto) {
        tourService.updateTour(id, dto);
        return "redirect:/admin/tours?success";
    }

    // 🔴 Show delete confirmation page
    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable Long id, Model model) {
        model.addAttribute("tour", tourService.getTourById(id));
        return "admin/delete-tour"; // → templates/admin/delete-tour.html
    }

    // 🔴 Handle delete
    @PostMapping("/delete/{id}")
    public String deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
        return "redirect:/admin/tours?deleted";
    }
}
