package com.app.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "Flights")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "ticket")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long FlightID;

    private String Airlines;
    private LocalDate Departure;
    private LocalDate Arrival;
    private String toArrival;
    private String fromDestination;
    private int Capacity;
    private int stops;
    private double EconomyPrice;
    private double BusinessClassPrice;

    @OneToMany(mappedBy = "flight",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;
    
}