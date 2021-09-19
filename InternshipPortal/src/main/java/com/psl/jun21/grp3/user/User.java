package com.psl.jun21.grp3.user;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.psl.jun21.grp3.applicant.Applicant;
import com.psl.jun21.grp3.company.Company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * TODO 
 * Author Rohan Sachin Suchitra
 * */
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
@JsonSubTypes({ @Type(value = Company.class, name = "company"), @Type(value = Applicant.class, name = "applicant"),
		@Type(value = SystemAdmin.class, name = "sys_admin") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Email
	private String email;

	/* Replaced by String companyName in Company.java
	 * 			and Name name in Applicant.java
	 * @Embedded
	private Name name;
	*/
	
	@NotNull
	@Column(updatable = false)
	private UserRole role;

	@JsonIgnore
	private String password;

	@NotNull
	@Size(min = 10, max = 10, message = "Contact no should be 10 digits")
	private int contactNo;

	public User(@NotNull @Email String email, Name name, @NotNull UserRole role, String password,
			@NotNull @Size(min = 10, max = 10, message = "Contact no should be 10 digits") int contactNo) {
		super();
		this.email = email;
		// this.name = name;
		this.role = role;
		this.password = password;
		this.contactNo = contactNo;
	}
	
}
