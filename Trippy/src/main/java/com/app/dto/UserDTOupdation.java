package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTOupdation {
	private String userName;
	 @Digits(integer = 10, fraction = 0)
	private long phoneNo;
    private String gender;
    private LocalDate DateOfBirth;
    private String Address;
}
