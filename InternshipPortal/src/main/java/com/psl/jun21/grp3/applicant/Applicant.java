package com.psl.jun21.grp3.applicant;

import java.util.List;
import javax.persistence.CascadeType;
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

import com.psl.jun21.grp3.internshipapplication.InternshipApplication;
import com.psl.jun21.grp3.user.Name;
import com.psl.jun21.grp3.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * TODO 
 * Author Roshini Rishitha
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	protected Name name;

	@NotNull
	@Min(value = 1000000000L)
	@Max(value = 9999999999L)
	protected long contactNo;

	protected String specialization;

	protected String degree;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	protected User user;

	@OneToMany(mappedBy = "applicant")
	List<InternshipApplication> internshipApplications;

}
