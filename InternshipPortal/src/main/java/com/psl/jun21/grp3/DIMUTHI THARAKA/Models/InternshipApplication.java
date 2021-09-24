package com.psl.training.Employee_Management.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ch.qos.logback.core.subst.Token.Type;

@Entity
@Table(name = "Internship_Application")
public class InternshipApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//private Applicant applicant;
	//private InternshipProfile internshipprofile;
	private String status;
	
	public InternshipApplication(String status) {
		super();
		this.status = status;
	}
	//@Column(name="InternshipApplication_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name ="Applicant_ID")
//	public Applicant getApplicant() {
//		return applicant;
//	}
//	
//	public void setApplicant(Applicant applicant) {
//		this.applicant = applicant;
//	}
//	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name ="Internship_ID")
//	public InternshipProfile getInternshipprofile() {
//		return internshipprofile;
//	}
//	
//	public void setInternshipprofile(InternshipProfile internshipprofile) {
//		this.internshipprofile = internshipprofile;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
