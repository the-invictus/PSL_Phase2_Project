package com.psl.jun21.grp3.user;
	
import org.springframework.security.core.userdetails.UserDetailsService;

import com.psl.jun21.grp3.company.Company;

public interface CompanyService extends UserDetailsService{
	Company save(CompanyRegistrationDto companyRegistrationDto);
}