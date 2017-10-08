package com.example.cinema_spring_app.model.repo;


import com.example.cinema_spring_app.model.Movie;

import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Modifying
    @Transactional
    @Query("update Movie m set m.title = :newTitle where m.id = :id")
    public void updateTitle (@Param("id") Integer id, @Param("newTitle") String newTitle);

    @Modifying
    @Transactional
    @Query("update Movie m set m.year = :newYear where m.id = :id")
    public void updateYear (@Param("id") Integer id, @Param("newYear") Date newYear);

    @Modifying
    @Transactional
    @Query("update Movie m set m.director = :newDirector where m.id = :id")
    public void updateDirector (@Param("id") Integer id, @Param("newDirector") String newDirector);

    @Modifying
    @Transactional
    @Query("update Movie m set m.duration = :newDuration where m.id = :id")
    public void updateDuration (@Param("id") Integer id, @Param("newDuration") int newDuration);

    @Modifying
    @Transactional
    @Query("update Movie m set m.ageCategory = :newAgeCategory where m.id = :id")
    public void updateCategory (@Param("id") Integer id, @Param("newAgeCategory") MovieCategory newAgeCategory);

    @Modifying
    @Transactional
    @Query("update Movie m set m.genre = :newGenre where m.id = :id")
    public void updateGenre (@Param("id") Integer id, @Param("newGenre") MovieGenre newGenre);


}
