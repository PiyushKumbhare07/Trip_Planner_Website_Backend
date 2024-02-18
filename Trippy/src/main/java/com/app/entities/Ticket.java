package com.app.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

@Entity
@Table(name = "Tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long TicketID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BookingID")
    @JsonIgnore
    private Bookings booking;

    @OneToOne
    @JoinColumn(name = "FlightID")
    private Flight flight;
    private String Type;
    @OneToOne
    @JoinColumn(name="HolidayID")
    private Holiday holiday;
    @Column(columnDefinition = "double default 0.0")
    private int price;
    private String SeatNo;

    @OneToOne(mappedBy = "ticket")
    private Traveller traveller;

    @CreatedDate
    private LocalDate DateOfPurchase;
}
