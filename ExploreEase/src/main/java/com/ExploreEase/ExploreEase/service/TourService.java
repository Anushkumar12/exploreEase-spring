package com.ExploreEase.ExploreEase.service;

import java.util.List;
import com.ExploreEase.ExploreEase.dto.TourDTO;

public interface TourService {
    TourDTO createTour(TourDTO dto);
    List<TourDTO> getAllTours();
    TourDTO getTourById(Long id);
    TourDTO updateTour(Long id, TourDTO dto);
    void deleteTour(Long id);
    long countTours();
}
