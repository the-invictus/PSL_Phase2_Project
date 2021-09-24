package com.psl.training.Employee_Management.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int company_id;
	
	@Column(name = "company_name")
	 private String name;
	
	@Column(name = "domain")
	 private String domain;
	
	@Column(name = "contact_no")
	 private String contact_no;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn( name = "ci_fid", referencedColumnName = "company_id")
	List<InternshipProfile> internshipProfiles = new ArrayList<>();
	
	public Company(int company_id, String name, String domain, String contact_no) {
		super();
		this.company_id = company_id;
		this.name = name;
		this.domain = domain;
		this.contact_no = contact_no;
	}
	public Company() {
		
	}
	
	public List<InternshipProfile> getInternshipProfiles() {
		return internshipProfiles;
	}
	public void setInternshipProfiles(List<InternshipProfile> internshipProfiles) {
		this.internshipProfiles = internshipProfiles;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
	
}
