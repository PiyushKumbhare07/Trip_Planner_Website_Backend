package com.app.entities;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @Column(name = "arrival_time", columnDefinition = "TIME")
    private LocalTime arrivalTime;
    
    @Column(name = "departure_time", columnDefinition = "TIME")
    private LocalTime departureTime;
    private String toArrival;
    private String fromDestination;
    private int Capacity;
    private int stops;
    private int EconomyPrice;
    private int BusinessClassPrice;

    @OneToMany(mappedBy = "flight",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;
    
}
