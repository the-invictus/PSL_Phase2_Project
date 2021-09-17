package com.psl.jun21.grp3.user;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.psl.jun21.grp3.applicant.Applicant;
import com.psl.jun21.grp3.company.Company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * TODO 
 * Author Rohan Sachin Suchitra
 * */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@JsonSubTypes({@Type(value = Company.class, name = "company"),
	@Type(value = Applicant.class, name = "applicant"),
	@Type(value = SystemAdmin.class, name = "sys_admin")})
public class User {

}
