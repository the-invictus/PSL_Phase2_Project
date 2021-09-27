package com.psl.jun21.grp3.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRepository;
import com.psl.jun21.grp3.user.UserRole;

/**
 * @author Rushikesh Dimuthi
 *
 */
@Service
public class CompanyService {

  private final Logger log = LoggerFactory.getLogger(CompanyService.class);

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private UserRepository userRepository;

  public Company save(CompanyRegistrationDto companyDto) {

    String password = passwordEncoder.encode(companyDto.getPassword());
    User user = new User();
    user.setEmail(companyDto.getEmail());
    user.setPassword(password);
    user.setRole(UserRole.COMPANY);

    Company company = new Company();
    company.setCompanyName(companyDto.getCompanyName());
    company.setContactNo(Long.parseLong(companyDto.getContactNo()));
    company.setDomain(companyDto.getDomain());

    user.setCompany(company);
    user = userRepository.save(user);
    return user.getCompany();
  }

  public Company getCompanyDetails(Long id) {
    return companyRepository.findByUserId(id);
  }

  public void updateCompany(Company company) {
    companyRepository.save(company);
  }
}
