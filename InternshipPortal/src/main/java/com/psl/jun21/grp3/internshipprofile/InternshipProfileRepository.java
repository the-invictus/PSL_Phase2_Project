package com.psl.jun21.grp3.internshipprofile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.psl.jun21.grp3.company.Company;

/**
 * @author Rushikesh
 *
 */
@Repository
public interface InternshipProfileRepository extends CrudRepository<InternshipProfileAppli, Long> {
	List<InternshipProfileAppli> findByCompany(Company c);

}
