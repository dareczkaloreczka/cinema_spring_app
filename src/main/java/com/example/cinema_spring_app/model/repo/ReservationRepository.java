package com.example.cinema_spring_app.model.repo;

import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    public List<Reservation> findBySeance(Seance seance);
}
