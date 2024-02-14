package com.app.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private long userID;
    private String userName;
    @Email
    private String email;
    @Digits(integer = 10, fraction = 0)
    private long phoneNo;
    private String password;
    @JsonIgnore
    private List<BookingDTO> bookings;
}
