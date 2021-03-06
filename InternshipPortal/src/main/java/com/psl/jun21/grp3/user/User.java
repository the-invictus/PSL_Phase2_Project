package com.psl.jun21.grp3.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@NotNull
	@Email
	protected String email;

	@NotNull
	@Column(updatable = false)
	protected UserRole role;

	@JsonIgnore
	protected String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Company company;

	@OneToOne(cascade = CascadeType.ALL)
	private Applicant applicant;

	public User(@NotNull @Email String email, @NotNull UserRole role, String password) {
		super();
		this.email = email;
		this.role = role;
		this.password = password;
	}

}
