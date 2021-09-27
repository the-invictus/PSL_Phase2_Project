package com.psl.jun21.grp3.applicant;

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
public class ApplicantRegistrationDto {

	private String email;
	private String firstName;
	private String middleName;
	private String surname;
	private String password;
	private String contactNo;
	private String specialization;
	private String degree;
	
}
