package com.psl.jun21.grp3.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.jun21.grp3.applicant.Applicant;

@Service
public class ApplicantService {

	private final Logger log = LoggerFactory.getLogger(ApplicantService.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ApplicantRepository applicantRepository;

	@Autowired
	private UserRepository userRepository;

	public Applicant save(ApplicantRegistrationDto applicantDto) {

		String password = passwordEncoder.encode(applicantDto.getPassword());
		User user = new User(applicantDto.getEmail(), UserRole.APPLICANT, password);
		user = userRepository.save(user);
		log.info("User created : {}", user.getEmail());

		Name name = new Name(applicantDto.getFirstName(), applicantDto.getMiddleName(), applicantDto.getSurname());
		Applicant newApplicant = new Applicant(name, Long.parseLong(applicantDto.getContactNo()), user.getId());
		applicantRepository.save(newApplicant);
		log.info("Applicant created : {}", name.toString());

		return null;
	}
}
