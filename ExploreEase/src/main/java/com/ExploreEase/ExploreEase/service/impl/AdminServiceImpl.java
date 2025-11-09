package com.ExploreEase.ExploreEase.service.impl;

import org.springframework.stereotype.Service;

import com.ExploreEase.ExploreEase.repository.TourRepository;
import com.ExploreEase.ExploreEase.repository.UserRepository;
import com.ExploreEase.ExploreEase.service.AdminService;


@Service

public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository = null;
    private final TourRepository tourRepository = null;

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long countTours() {
        return tourRepository.count();
    }
}
