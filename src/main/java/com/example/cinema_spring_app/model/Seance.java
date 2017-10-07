package com.example.cinema_spring_app.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    private Time time;

    @ManyToOne
    @JoinColumn
    private Movie movie;
    @ManyToOne
    private CinemaHall hall;
    @OneToMany
    private List<Reservation> reservations;


    public Seance() {
    }

   public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservationList) {
        this.reservations = reservationList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaHall getHall() {
        return hall;
    }

    public void setHall(CinemaHall hall) {
        this.hall = hall;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }





}
