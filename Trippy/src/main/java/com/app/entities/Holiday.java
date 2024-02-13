package com.app.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "holidays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holidayID;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String packageName;
    
    @Column(nullable = false)
    private int totalDays;
    
    private String description;
    
    @Column(nullable = false)
    private double pricePerAdult;
    
    @Column(nullable = false)
    private double pricePerChild;
    
    @OneToMany(mappedBy = "holiday")
    @JsonIgnore
    private List<Ticket> tickets;
    
    @Column(nullable = false)
    private LocalDate startDate;
    
    @Column(nullable = false)
    private LocalDate endDate;
    
    @Lob
    private byte[] imageBlob;
   
}

