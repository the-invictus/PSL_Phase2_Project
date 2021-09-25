package com.psl.training.Employee_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.training.Employee_Management.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
