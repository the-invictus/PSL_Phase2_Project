package com.psl.jun21.grp3.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.jun21.grp3.company.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	Company findByEmail(String email);
}