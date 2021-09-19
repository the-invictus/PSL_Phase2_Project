package com.psl.jun21.grp3.user;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;

@Embeddable
@AllArgsConstructor
public class Name {

	private String firstName;
	private String middleName;
	private String surname;

}