package com.ExploreEase.ExploreEase.service;

import java.util.List;
import java.util.Optional;

import com.ExploreEase.ExploreEase.entity.User;

public interface UserService {
    User registerUser(User user);
    Optional<User> findByEmail(String email);
    List<User> getAllUsers();
	long countUsers();
	void deleteUserById(Long id);
	 String login(String email, String password) ;
}