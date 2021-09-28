package com.psl.jun21.grp3.internshipapplication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.psl.jun21.grp3.applicant.Applicant;
import com.psl.jun21.grp3.company.Company;
import com.psl.jun21.grp3.user.Name;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)

public class InternshipApplicationServiceTest {

	@InjectMocks
	private InternshipApplicationServiceImpl service;
	@Mock
	private InternshipApplicationRepository repository;

	@org.junit.jupiter.api.Test
	public void getApplicationByIdTest() {

		long id = 1;
		Optional<InternshipApplication> i1 = Optional.of(new InternshipApplication());
		i1.get().setInternship_app_id(1);
		i1.get().setApplicationStatus(ApplicationStatus.PENDING);
		when(repository.findById(1L)).thenReturn(i1);
		assertEquals(1L, service.getApplicationById(id).getInternship_app_id());
		
	}
	
	@Test
	public void saveTest() {
		InternshipApplication i1 = (new InternshipApplication());
		i1.setApplicationStatus(ApplicationStatus.APPROVED);
		when(repository.save(any())).thenReturn(i1);
	    assertThat(service.save(i1).getApplicationStatus())
	        .isEqualTo(ApplicationStatus.APPROVED);
			
	}
		
		
		
		
		
		
		
		
		// i1.setAppli
		// edOn(new Date());

		// -----------------------------applicant

//		Applicant a1 = new Applicant();
//		a1.setId(1);
//		a1.setName(new Name("Dimuthi","tharaka","Silva"));
//		a1.setContactNo(9341267808L);
//		a1.setSpecialization("Software Engineering");
//		a1.setDegree("Bsc");

		// ---------------company
//		Company c1 = new Company();
//		c1.setId(2);
//		c1.setCompanyName("Persistent System");
//		c1.setDomain("IT");
//		c1.setContactNo(9341267808L);

//		InternshipApplication i1 = new InternshipApplication();
//		i1.setApplicant(a1);
//		i1.setApplicationStatus(ApplicationStatus.PENDING);
//		i1.setAppliedOn(new Date());
//		i1.setInternship_app_id(1L);
//		i1.setInternshipProfile(null);

	
}
