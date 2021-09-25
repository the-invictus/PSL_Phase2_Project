package com.psl.training.Employee_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.psl.training.Employee_Management.model.InternshipProfile;

@Repository
public interface InternJobRepository extends JpaRepository<InternshipProfile, Long> {
	
	@Query(value = "SELECT i.internship_profile_id,i.description,i.domain,i.duration,i.title FROM internship_profile i WHERE i.ci_fid =:param", nativeQuery = true)
    List<InternshipProfile> getInternshipListFromCompanyById(@Param("param") int param);

}
