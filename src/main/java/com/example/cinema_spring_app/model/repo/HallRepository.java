package com.example.cinema_spring_app.model.repo;

import com.example.cinema_spring_app.model.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<CinemaHall, Integer> {
}
