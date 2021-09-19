package com.psl.jun21.grp3.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.psl.jun21.grp3.applicant.Applicant;

public interface ApplicantService extends UserDetailsService {
	Applicant save(ApplicantRegistrationDto applicantRegistrationDto);

}
