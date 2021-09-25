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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "InternshipProfile")
public class InternshipProfile {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long internshipProfile_id;

		private String title;

		private String domain;

		private String description;

		private int duration;
		
//		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//		@JoinTable(name = "internship_application",
//			joinColumns = { @JoinColumn(name = "internshipProfile_id")},
//			inverseJoinColumns = { @JoinColumn (name = "applicant_id")})
		//private Set<InternshipApplication> internapp = new HashSet<InternshipApplication>();
		
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn( name = "jobapplication_fid", referencedColumnName = "internshipProfile_id")
		List<InternshipApplication> internshipApplications = new ArrayList<>();
		
		
		public InternshipProfile() {
			
		}
		
		public InternshipProfile(String title, String domain, String description, int duration) {
			super();
			this.title = title;
			this.domain = domain;
			this.description = description;
			this.duration = duration;
		}
		
		public Long getInternshipProfile_id() {
			return internshipProfile_id;
		}

		public void setInternshipProfile_id(Long internshipProfile_id) {
			this.internshipProfile_id = internshipProfile_id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public List<InternshipApplication> getInternshipApplications() {
			return internshipApplications;
		}

		public void setInternshipApplications(List<InternshipApplication> internshipApplications) {
			this.internshipApplications = internshipApplications;
		}
		
		
//		@OneToMany(mappedBy = "job")
//		public Set<InternshipApplication> getInternapp() {
//			return internapp;
//		}
//
//		public void setInternapp(Set<InternshipApplication> internapp) {
//			this.internapp = internapp;
//		}
//		public void addInternshipApplication(InternshipApplication internapp) {
//	        this.internapp.add(internapp);
//	    } 

		

		
		
	
}
