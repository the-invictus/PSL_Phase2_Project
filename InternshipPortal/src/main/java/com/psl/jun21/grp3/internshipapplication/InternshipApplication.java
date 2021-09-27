package com.psl.jun21.grp3.internshipapplication;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.psl.jun21.grp3.applicant.Applicant;
import com.psl.jun21.grp3.internshipprofile.InternshipProfile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author rishitha roshini
 *
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class InternshipApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long internship_app_id;

	private Date appliedOn;

	private ApplicationStatus applicationStatus;

	@ManyToOne
	Applicant applicant;

	@ManyToOne
	InternshipProfile internshipProfile;

}
