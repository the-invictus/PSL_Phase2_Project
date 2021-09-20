package com.psl.jun21.grp3.applicant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.psl.jun21.grp3.user.Name;

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

	protected long userId;

	public Applicant(Name name, @NotNull @Min(1000000000) @Max(9999999999L) long contactNo, long userId) {
		super();
		this.name = name;
		this.contactNo = contactNo;
		this.userId = userId;
	}

}
