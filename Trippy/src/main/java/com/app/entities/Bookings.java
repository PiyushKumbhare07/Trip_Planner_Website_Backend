package com.app.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long BookingID;

    @CreatedDate
    private LocalDate BookingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID")
    private User user;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public Bookings(User user, LocalDate BookingDate) {
        this.BookingDate = BookingDate;
        this.user = user;
    }

    public void addTicket(Ticket t) {
        tickets.add(t);
        t.setBooking(this);
    }

    public void removeTicket(Ticket s) {
        tickets.remove(s);
        s.setBooking(null);
    }
}
