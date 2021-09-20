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
public class CompanyRegistrationDto {

	private String email;
	private String companyName;
	private String password;
	private String contactNo;
	private String domain;

}
