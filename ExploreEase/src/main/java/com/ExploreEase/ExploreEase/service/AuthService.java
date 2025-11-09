package com.ExploreEase.ExploreEase.service;

import com.ExploreEase.ExploreEase.entity.User;

public interface AuthService {
    User registerUser(User user);
    String login(String email, String password);
}
