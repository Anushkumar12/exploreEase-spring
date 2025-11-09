package com.ExploreEase.ExploreEase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ExploreEase.ExploreEase.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}