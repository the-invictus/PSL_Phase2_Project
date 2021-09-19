package com.psl.jun21.grp3.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationDto {

	private String email;
	private String firstName;
	private String middleName;
	private String surname;
	private String role;
	private String password;
	private String contactNo;

}
