package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.CinemaHall;
import com.example.cinema_spring_app.model.repo.HallRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class HallController {

    private final HallRepository hallRepository;

    public HallController(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<CinemaHall> getAllHalls(){
        return hallRepository.findAll();
    }
}
