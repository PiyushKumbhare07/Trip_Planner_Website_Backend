package com.app.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true,of = "email")
public class User{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long UserID;
@Column(name="userName",length=20)
private String userName;
@Email
@Column(unique = true)
private String email;
@Digits(integer = 10, fraction = 0)
private long phoneNo;
private String gender;
private LocalDate DateOfBirth;
private String Address;
private String password;
@OneToMany(mappedBy = "user",fetch = FetchType.EAGER, 
cascade = CascadeType.ALL, 
orphanRemoval = true )
@JsonIgnore
private List<Bookings> bookings;
//@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//@JoinColumn(name = "UserID")
//private List<Bookings> bookings;
public void setPassword(String password) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    this.password = encoder.encode(password);
}
public void addBooking(Bookings b) {
	bookings.add(b);
	b.setUser(this);
}
public void removeBookings(Bookings s) {
	bookings.remove(s);
	s.setUser(null);
}
}






















