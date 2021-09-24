package com.psl.training.Employee_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.psl.training.Employee_Management.model.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	
	@Query(value = "select * from applicants where applicant_id IN (Select applicantapplication_fid from internship_application where  jobapplication_fid=:param)",nativeQuery = true)
	public List<Applicant> getApplicantsByjobId(@Param("param") long param);

}
