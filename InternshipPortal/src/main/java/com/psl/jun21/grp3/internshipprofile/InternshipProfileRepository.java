package com.psl.jun21.grp3.internshipprofile;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psl.jun21.grp3.company.Company;

/**
 * @author Rushikesh
 *
 */
@Repository
public interface InternshipProfileRepository extends CrudRepository<InternshipProfile, Long> {
	List<InternshipProfile> findByCompany(Company c);

	// Applicant can not apply for past profiles where status is pending 0 or
	// approved 1
	@Query(value = "SELECT * FROM internship_profile i WHERE i.id NOT IN(SELECT a.internship_app_id FROM internship_application a WHERE a.applicant_id=:a_id AND a.application_status IN (0,1))", nativeQuery = true)
	List<InternshipProfile> getApplicableProfilesByApplicantId(@Param("a_id") long a_id);

	// Applicant applied profile history
	@Query(value = "SELECT p.title, p.description, p.duration, a.applied_on ,a.application_status FROM internship_profile p INNER JOIN internship_application a ON p.id = a.internship_profile_id WHERE a.applicant_id=:a_id", nativeQuery = true)
	List<Object[]> getProfilesByApplicantId(@Param("a_id") long a_id);

}