package com.psl.jun21.grp3.applicant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.jun21.grp3.user.Name;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRepository;
import com.psl.jun21.grp3.user.UserRole;

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
		Name name = new Name(applicantDto.getFirstName(), applicantDto.getMiddleName(), applicantDto.getSurname());
		User user = new User();
		user.setEmail(applicantDto.getEmail());
		user.setPassword(password);
		user.setRole(UserRole.APPLICANT);

		Applicant applicant = new Applicant();
		applicant.setContactNo(Long.parseLong(applicantDto.getContactNo()));
		applicant.setDegree(applicantDto.getDegree());
		applicant.setName(name);
		applicant.setSpecialization(applicantDto.getSpecialization());
		user.setApplicant(applicant);
		user = userRepository.save(user);
		log.info("Applicant created Name: {} email:{}", name.toString(), user.getEmail());
		return user.getApplicant();
	}

	public Applicant findApplicantById(long id) {
		return applicantRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	public long deleteApplicantById(long id) {
		applicantRepository.deleteById(id);
		log.info("Applicant deleted ID: {}", id);
		return id;
	}

	public Applicant updateApplicant(Applicant applicant) {
		log.info("Applicant updated ID: {}", applicant.getId());
		return applicantRepository.save(applicant);
	}

}
