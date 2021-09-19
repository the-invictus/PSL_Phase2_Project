package com.psl.jun21.grp3.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.jun21.grp3.applicant.Applicant;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	// TODO 
	User findByEmail(String email);
}

/*
 * @Repository public interface UserRepository extends JpaRepository<Applicant,
 * Long>{ 
 * Applicant findByEmail(String email); }
 */