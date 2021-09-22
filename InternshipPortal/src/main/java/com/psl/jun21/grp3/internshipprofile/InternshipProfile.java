package com.psl.jun21.grp3.internshipprofile;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.psl.jun21.grp3.company.Company;
import com.psl.jun21.grp3.internshipapplication.InternshipApplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rushikesh
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternshipProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String domain;

	private String description;

	private int duration;

	@ManyToOne(optional = false)
	Company company;

	@OneToMany(mappedBy = "internshipProfile")
	List<InternshipApplication> internshipApplications;

}
