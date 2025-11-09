package com.ExploreEase.ExploreEase.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ExploreEase.ExploreEase.entity.Role;
import com.ExploreEase.ExploreEase.entity.Tour;
import com.ExploreEase.ExploreEase.entity.User;
import com.ExploreEase.ExploreEase.repository.RoleRepository;
import com.ExploreEase.ExploreEase.repository.TourRepository;
import com.ExploreEase.ExploreEase.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            TourRepository tourRepository
    ) {
        return args -> {

            // --- Create roles if not exist ---
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
                Role r = new Role();
                r.setName("ROLE_ADMIN");
                return roleRepository.save(r);
            });

            Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
                Role r = new Role();
                r.setName("ROLE_USER");
                return roleRepository.save(r);
            });

            // --- Create default admin user ---
            if (userRepository.findByEmail("anush@exploreease.com").isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                User admin = new User();
                admin.setName("anush");
                admin.setEmail("anush@exploreease.com");
                admin.setPassword(encoder.encode("anush123"));
                admin.setRoles(new HashSet<>(List.of(adminRole)));
                userRepository.save(admin);
                System.out.println("✅ Default admin created: admin@exploreease.com / admin123");
            }

            // --- Create sample tours ---
            /* if (tourRepository.count() == 0) {
                Tour tour1 = new Tour();
                tour1.setTitle("Kerala Backwaters Retreat");
                tour1.setDescription("Explore the serene backwaters and lush landscapes of Kerala.");
                tour1.setLocation("Kerala, India");
                tour1.setPrice(15000);
                tour1.setDurationDays(3);
                tour1.setSeatsAvailable(10);
                tour1.setImageUrl("https://example.com/kerala.jpg");

                Tour tour2 = new Tour();
                tour2.setTitle("Jaipur Heritage Tour");
                tour2.setDescription("Experience the royal heritage of Jaipur with palace visits and cultural shows.");
                tour2.setLocation("Jaipur, Rajasthan");
                tour2.setPrice(12000);
                tour2.setDurationDays(2);
                tour2.setSeatsAvailable(15);
                tour2.setImageUrl("https://example.com/jaipur.jpg");

                Tour tour3 = new Tour();
                tour3.setTitle("Manali Adventure Camp");
                tour3.setDescription("Enjoy adventure sports and scenic beauty in the Himalayas.");
                tour3.setLocation("Manali, Himachal Pradesh");
                tour3.setPrice(18000);
                tour3.setDurationDays(4);
                tour3.setSeatsAvailable(20);
                tour3.setImageUrl("https://example.com/manali.jpg");

                tourRepository.saveAll(Arrays.asList(tour1, tour2, tour3));
                System.out.println("✅ Sample tours added to database.");
            } */
        };
    }
}
