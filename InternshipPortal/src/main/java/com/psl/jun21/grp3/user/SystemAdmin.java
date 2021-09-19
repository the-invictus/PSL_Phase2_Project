package com.psl.jun21.grp3.user;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.ToString;

/**
 * @author Suchitra Rohan Sachin
 *
 */
@Entity
@ToString
public class SystemAdmin extends User {

	public SystemAdmin(@NotNull @Email String email, Name name, @NotNull UserRole role, String password,
			@NotNull @Size(min = 10, max = 10, message = "Contact no should be 10 digits") int contactNo) {
		super(email, name, role, password, contactNo);
	}
	
}
