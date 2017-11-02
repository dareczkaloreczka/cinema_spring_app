package com.example.cinema_spring_app.model.repo;

import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.TicketOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    @Modifying
    @Transactional
    @Query("update Reservation r set r.customerName = :newCustomerName where r.id = :id")
     void updateCustomerName(@Param("id") Integer id, @Param("newCustomerName") String customerName);

    @Modifying
    @Transactional
    @Query("update Reservation r set r.customerEmailAddress = :newCustomerEmailAddress where r.id = :id")
     void updateCustomerEmailAddress(@Param("id") Integer id, @Param("newCustomerEmailAddress") String customerEmailAddress);

    @Modifying
    @Transactional
    @Query("update Reservation r set r.row = :newRow where r.id = :id")
     void updateRow(@Param("id") Integer id, @Param("newRow") String row);

    @Modifying
    @Transactional
    @Query("update Reservation r set r.seat = :newSeat where r.id = :id")
     void updateSeat(@Param("id") Integer id, @Param("newSeat") int seat);

    @Modifying
    @Transactional
    @Query("update Reservation r set r.ticketOption = :newTicketOption where r.id = :id")
     void updateTicketOption(@Param("id") Integer id, @Param("newTicketOption")TicketOption ticketOption);
}
