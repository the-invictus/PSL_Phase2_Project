package com.psl.training.Employee_Management.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.training.Employee_Management.model.Company;
import com.psl.training.Employee_Management.repository.CompanyRepository;
import com.psl.training.Employee_Management.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllDetails() {
		return companyRepository.findAll();
		
	}

	@Override
	public Company getCompanyById(Integer id) {
		
		return companyRepository.findById(id).get();
	}

	@Override
	public Company updateCompany(Company company) {
		
		return companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Integer id) {
		companyRepository.deleteById(id);
		
	}

}
