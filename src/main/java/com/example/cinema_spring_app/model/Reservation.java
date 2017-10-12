package com.example.cinema_spring_app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerName;
    private String customerEmailAddress;
    private String row;
    private int seat;
    private BigDecimal price;
    private TicketOption ticketOption;

    @ManyToOne
    @JoinColumn(name="seance_id", nullable=false)
    private Seance seance;

    @Transient
    private final BigDecimal STANDARD_PRICE;

    public Reservation() {
        STANDARD_PRICE = BigDecimal.valueOf(26.00);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public BigDecimal getPrice() {
        return price;
    }



    public TicketOption getTicketOption() {
        return ticketOption;
    }

    public void setTicketOption(TicketOption ticketOption) {
        this.ticketOption = ticketOption;
    }

    public BigDecimal getSTANDARD_PRICE() {
        return STANDARD_PRICE;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public void setPrice() {
        price = STANDARD_PRICE.multiply(ticketOption.getFactor());
    }
}
