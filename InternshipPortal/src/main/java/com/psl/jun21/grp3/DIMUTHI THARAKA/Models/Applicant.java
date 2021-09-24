package com.psl.training.Employee_Management.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "applicants")
public class Applicant {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private long applicant_id;
		private String first_name;
		private String middle_name;
		private String surname;
		private String degree;
		private String specialization;
		private String contact_no;
		
		//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "applicants")
		//private Set<InternshipApplication> internapp = new HashSet<InternshipApplication>();
		
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn( name = "applicantapplication_fid", referencedColumnName = "applicant_id")
		List<InternshipApplication> internshipApplications = new ArrayList<>();
		
		public Applicant() {
			
		}

		public Applicant(String first_name, String middle_name, String surname, String degree, String specialization,
				String contact_no) {
			super();
			this.first_name = first_name;
			this.middle_name = middle_name;
			this.surname = surname;
			this.degree = degree;
			this.specialization = specialization;
			this.contact_no = contact_no;
		}
//		public void addJob(InternshipApplication internapp) {
//	        this.internapp.add(internapp);
//	    }
		
		
		public long getApplicant_id() {
			return applicant_id;
		}

		public void setApplicant_id(long applicant_id) {
			this.applicant_id = applicant_id;
		}

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getMiddle_name() {
			return middle_name;
		}

		public void setMiddle_name(String middle_name) {
			this.middle_name = middle_name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getDegree() {
			return degree;
		}

		public void setDegree(String degree) {
			this.degree = degree;
		}

		public String getSpecialization() {
			return specialization;
		}

		public void setSpecialization(String specialization) {
			this.specialization = specialization;
		}

		public String getContact_no() {
			return contact_no;
		}

		public void setContact_no(String contact_no) {
			this.contact_no = contact_no;
		}

		public List<InternshipApplication> getInternshipApplications() {
			return internshipApplications;
		}

		public void setInternshipApplications(List<InternshipApplication> internshipApplications) {
			this.internshipApplications = internshipApplications;
		}
		
		
//		public void addInternshipApplication(InternshipApplication internapp) {
//	        this.internapp.add(internapp);
//	    } 

		
		
		
		
		

		
}
