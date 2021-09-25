package com.psl.training.Employee_Management.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.training.Employee_Management.model.InternshipProfile;
import com.psl.training.Employee_Management.repository.InternJobRepository;
import com.psl.training.Employee_Management.service.InternshipJobService;

@Service
public class InternJobServiceImpl implements InternshipJobService {
	
	@Autowired
	private InternJobRepository internjobrepository;
	
	@Override
	public List<InternshipProfile> getInternshipListFromCompanyById(Integer id) {
		
		return internjobrepository.getInternshipListFromCompanyById(id);
	}

}
