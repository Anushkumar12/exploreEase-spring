package com.ExploreEase.ExploreEase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ExploreEase.ExploreEase.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
 
	 Optional<User> findByEmail(String email);
	    boolean existsByEmail(String email);
	    long count(); // counts total users (already available by default in JpaRepository)
	    
	    void deleteById(Long id);
}
