package com.app.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "travellers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "ticket")
public class Traveller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travellerID;

    private String name;
    private int age;
    private String mobileNumber;
    private String gender;
    
    @OneToOne
    @JoinColumn(name="ticket_id")
    @JsonIgnore
    private Ticket ticket;
}

