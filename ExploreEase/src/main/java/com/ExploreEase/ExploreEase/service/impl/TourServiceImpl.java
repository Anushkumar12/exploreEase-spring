package com.ExploreEase.ExploreEase.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ExploreEase.ExploreEase.dto.TourDTO;
import com.ExploreEase.ExploreEase.entity.Tour;
import com.ExploreEase.ExploreEase.repository.TourRepository;
import com.ExploreEase.ExploreEase.service.TourService;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    private TourDTO mapToDTO(Tour tour) {
        return new TourDTO(
            tour.getId(),
            tour.getTitle(),
            tour.getDescription(),
            tour.getLocation(),
            tour.getPrice(),
            tour.getDurationDays(),
            tour.getSeatsAvailable(),
            tour.getImageUrl()
        );
    }

    private Tour mapToEntity(TourDTO dto) {
        Tour tour = new Tour();
        tour.setTitle(dto.getTitle());
        tour.setDescription(dto.getDescription());
        tour.setLocation(dto.getLocation());
        tour.setPrice(dto.getPrice());
        tour.setDurationDays(dto.getDurationDays());
        tour.setSeatsAvailable(dto.getSeatsAvailable());
        tour.setImageUrl(dto.getImageUrl());
        return tour;
    }


    @Override
    public TourDTO createTour(TourDTO dto) {
        Tour entity = mapToEntity(dto);
        entity.setId(null); // prevent accidental updates
        Tour saved = tourRepository.save(entity);
        return mapToDTO(saved);
    }

    

    @Override
    public List<TourDTO> getAllTours() {
        return tourRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO getTourById(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found with id: " + id));
        return mapToDTO(tour);
    }

    @Override
    public TourDTO updateTour(Long id, TourDTO dto) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found with id: " + id));

        tour.setTitle(dto.getTitle());
        tour.setDescription(dto.getDescription());
        tour.setLocation(dto.getLocation());
        tour.setPrice(dto.getPrice());
        tour.setDurationDays(dto.getDurationDays());
        tour.setSeatsAvailable(dto.getSeatsAvailable());
        tour.setImageUrl(dto.getImageUrl());

        return mapToDTO(tourRepository.save(tour));
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public long countTours() {
        return tourRepository.count();
    }
}
