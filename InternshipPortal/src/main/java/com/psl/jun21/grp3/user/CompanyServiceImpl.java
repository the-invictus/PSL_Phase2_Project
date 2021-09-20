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

import com.psl.jun21.grp3.company.Company;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CompanyRepository companyRepository;

	

	@Override
	public Company save(CompanyRegistrationDto companyDto) {
		Name name = new Name(companyDto.getFirstName(), companyDto.getMiddleName(),
				companyDto.getSurname());

		String password = passwordEncoder.encode(companyDto.getPassword());

		Company newCompany = new Company(companyDto.getEmail(), password,
				Long.parseLong(companyDto.getContactNo()), name);

		companyRepository.save(newCompany);
		return null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Company company = companyRepository.findByEmail(username);

		if (company == null) {
			throw new UsernameNotFoundException("Invalid username and password");
		}

		Collection<CompanyRole> companyRoles = new ArrayList<CompanyRole>(Arrays.asList(company.getRole()));

		return new org.springframework.security.core.userdetails.User(company.getEmail(), company.getPassword(),
				mapRolesToAuthorities(companyRoles));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
	}
}
