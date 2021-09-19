package com.psl.jun21.grp3.user;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.psl.jun21.grp3.applicant.Applicant;
import com.psl.jun21.grp3.company.Company;

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
	protected long id;

	@NotNull
	@Email
	protected String email;

	/*
	 * Replaced by String companyName in Company.java and Name name in
	 * Applicant.java
	 * 
	 * @Embedded private Name name;
	 */

	@NotNull
	@Column(updatable = false)
	protected UserRole role;

	@JsonIgnore
	protected String password;

	@NotNull
	@Min(value = 1000000000L)
	@Max(value = 9999999999L)
	protected long contactNo;

	public User(@NotNull @Email String email, @NotNull UserRole role, String password,
			@NotNull @Size(min = 10, max = 10, message = "Contact no should be 10 digits") long contactNo) {
		super();
		this.email = email;
		this.role = role;
		this.password = password;
		this.contactNo = contactNo;
	}

}
