package com.ExploreEase.ExploreEase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ExploreEase.ExploreEase.dto.TourDTO;
import com.ExploreEase.ExploreEase.service.TourService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping
    public ResponseEntity<List<TourDTO>> getAllTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable Long id) {
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    @PostMapping
    public ResponseEntity<TourDTO> createTour(@Valid @RequestBody TourDTO dto) {
        return ResponseEntity.ok(tourService.createTour(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourDTO> updateTour(@PathVariable Long id, @Valid @RequestBody TourDTO dto) {
        return ResponseEntity.ok(tourService.updateTour(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }
}
