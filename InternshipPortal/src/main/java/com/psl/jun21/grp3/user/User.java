package com.psl.jun21.grp3.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
/*
 * @MappedSuperclass
 * 
 * @JsonSubTypes({ @Type(value = Company.class, name = "company"), @Type(value =
 * Applicant.class, name = "applicant"),
 * 
 * @Type(value = SystemAdmin.class, name = "sys_admin") })
 */
@Entity
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

	public User(@NotNull @Email String email, @NotNull UserRole role, String password) {
		super();
		this.email = email;
		this.role = role;
		this.password = password;
	}
	
	

}
