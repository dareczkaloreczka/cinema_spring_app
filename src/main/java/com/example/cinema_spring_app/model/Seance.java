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
    @JoinColumn(name="movie_id", nullable=false)
    private Movie movie;
    @ManyToOne
    private CinemaHall hall;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "seance", orphanRemoval = true)
    @Column(nullable = false)
    private List<Reservation> reservations;

   public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservationList) {
        this.reservations = reservationList;
        this.reservations.stream().forEach(r -> r.setSeance(this));
    }
    public void addReservation (Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setSeance(this);
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
