package com.psl.training.Employee_Management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.psl.training.Employee_Management.model.Company;

public interface CompanyService {
	List<Company> getAllDetails();
	
	Company getCompanyById(Integer id);
	
	Company updateCompany(Company company);
	
	void deleteCompany(Integer id);
	
	
}
