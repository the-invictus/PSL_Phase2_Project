package com.psl.jun21.grp3.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.jun21.grp3.applicant.Applicant;

@Service
public class ApplicantServiceImpl implements ApplicantService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ApplicantRepository applicantRepository;

	@Override
	public Applicant save(ApplicantRegistrationDto applicantDto) {
		Name name = new Name(applicantDto.getFirstName(), applicantDto.getMiddleName(),
				applicantDto.getSurname());

		String password = passwordEncoder.encode(applicantDto.getPassword());

		Applicant newApplicant = new Applicant(applicantDto.getEmail(), password,
				Long.parseLong(applicantDto.getContactNo()), name);

		applicantRepository.save(newApplicant);
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Applicant user = applicantRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username and password");
		}

		Collection<UserRole> userRoles = new ArrayList<UserRole>(Arrays.asList(user.getRole()));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(userRoles));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
	}

}
