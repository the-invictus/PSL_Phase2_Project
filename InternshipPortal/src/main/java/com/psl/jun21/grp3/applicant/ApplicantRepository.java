package com.psl.jun21.grp3.applicant;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long> {

  @Query(
      value = "select * from applicant where id IN (select applicant_id from internship_application where interbship_profile_id=:profile_id)",
      nativeQuery = true)
  public List<Applicant> getApplicantsByProfileId(@Param("profile_id") long profile_id);

  @Query(
      value = "SELECT a.first_name,a.middle_name,a.surname, a.specialization, a.degree, a.contact_no, ia.applied_on, ia.application_status, ia.internship_app_id FROM applicant a INNER JOIN internship_application ia ON a.id = ia.applicant_id WHERE ia.internship_profile_id=:profile_id",
      nativeQuery = true)
  List<Object[]> getApplicationsByProfileId(@Param("profile_id") long profile_id);


}
