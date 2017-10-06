package com.example.cinema_spring_app.model.repo;


import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.TicketOption;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {

    public List<Seance> findByMovie(Movie movie, Sort sort);

    @Modifying
    @Transactional
    @Query("update Seance s set s.movie = :newMovie where s.id = :id")
    public void updateMovie(@Param("id") Integer id, @Param("newMovie") Movie movie);

    @Modifying
    @Transactional
    @Query("update Seance s set s.date = :newDate where s.id = :id")
    public void updateDate(@Param("id") Integer id, @Param("newDate") Date newDate);

    @Modifying
    @Transactional
    @Query("update Seance s set s.time = :newTime where s.id = :id")
    public void updateTime (@Param("id") Integer id, @Param("newTime") Time newTime);



}