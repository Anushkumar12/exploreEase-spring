package com.ExploreEase.ExploreEase.service;


public interface AdminService {
    long countUsers();
    void deleteUserById(Long id);
    long countTours();
}