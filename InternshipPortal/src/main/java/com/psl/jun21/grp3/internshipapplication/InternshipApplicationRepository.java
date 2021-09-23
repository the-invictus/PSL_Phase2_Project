package com.psl.jun21.grp3.internshipapplication;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InternshipApplicationRepository extends CrudRepository<InternshipApplication, Long> {

	@Query(value = "SELECT * FROM internship_application i WHERE i.applicant_id =:a_id AND i.internship_profile_id=:p_id AND i.application_status=0", nativeQuery = true)
	List<InternshipApplication> getPendingAppByApplicantIdProfileId(@Param("a_id") long a_id, @Param("p_id") long p_id);

}
