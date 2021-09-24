package com.psl.training.Employee_Management.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.training.Employee_Management.model.Applicant;
import com.psl.training.Employee_Management.repository.ApplicantRepository;
import com.psl.training.Employee_Management.service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService {
	@Autowired
	private ApplicantRepository applicantrepository;
	
	@Override
	public List<Applicant> getApplicantsByjobId(long id) {
		return applicantrepository.getApplicantsByjobId(id);
	}

}
