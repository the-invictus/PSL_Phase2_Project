package com.psl.jun21.grp3.internshipprofile;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rushikesh
 *
 */
@Repository
public interface InternshipProfileRepository extends CrudRepository<InternshipProfileAppli, Long> {

}
