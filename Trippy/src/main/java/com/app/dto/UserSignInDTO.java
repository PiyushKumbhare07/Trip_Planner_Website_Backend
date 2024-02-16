package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;

import com.app.entities.Ticket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignInDTO {
@Email
String email;
String password;
}
