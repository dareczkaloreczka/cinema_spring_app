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

    private double price;

    private TicketOption ticketOption;

    @ManyToOne
    @JoinColumn
    private Movie movie;
    //@OneToOne
    //private CinemaHall hall;
    //@OneToMany
    //private List<Reservation> reservations;

    @Transient
    private static final double STANDARD_PRICE = 26.00;

    public Seance() {
       // price = STANDARD_PRICE * ticketOption.getFactor();
    }

   /* public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservationList) {
        this.reservations = reservationList;
    }*/

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

    /*public CinemaHall getHall() {
        return hall;
    }

    public void setHall(CinemaHall hall) {
        this.hall = hall;
    }*/

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TicketOption getTicketOption() {
        return ticketOption;
    }

    public void setTicketOption(TicketOption ticketOption) {
        this.ticketOption = ticketOption;
    }


}
