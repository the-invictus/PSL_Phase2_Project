package com.psl.jun21.grp3.company;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.psl.jun21.grp3.internshipprofile.InternshipProfile;
import com.psl.jun21.grp3.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * TODO 
 * Author Rushikesh Dimuthi
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	protected String companyName;

	protected String domain;

	@NotNull
	@Min(value = 1000000000L)
	@Max(value = 9999999999L)
	protected long contactNo;

	@OneToOne
	@PrimaryKeyJoinColumn
	protected User user;

	@OneToMany(mappedBy = "company")
	List<InternshipProfile> internshipProfiles;

	public Company(String companyName, String domain, @NotNull @Min(1000000000) @Max(9999999999L) long contactNo) {
		super();
		this.companyName = companyName;
		this.domain = domain;
		this.contactNo = contactNo;
	}

}