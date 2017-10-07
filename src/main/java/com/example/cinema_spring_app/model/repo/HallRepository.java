package com.example.cinema_spring_app.model.repo;

import com.example.cinema_spring_app.model.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<CinemaHall, Integer> {


}
