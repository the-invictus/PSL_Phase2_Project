package com.psl.jun21.grp3.company;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

  @InjectMocks
  CompanyService companyService;

  @Mock
  CompanyRepository companyRepository;

  @Mock
  UserRepository userRepository;

  @Mock
  BCryptPasswordEncoder passwordEncoder;

  @Test
  void testSave() {
    when(userRepository.save(any())).thenReturn(new User());
    when(passwordEncoder.encode("")).thenReturn("");
    assertThat(companyService.save(new CompanyRegistrationDto("", "", "", "2", "")))
        .isEqualTo(null);
  }

  @Test
  void testGetCompanyDetails() {
    when(companyRepository.findByUserId(2L)).thenReturn(new Company("PSL", "IT", 0L));
    assertThat(companyService.getCompanyDetails(2L).getDomain()).isEqualTo("IT");
  }

  @Test
  void testUpdateCompany() {
    Company company = new Company();
    company.setCompanyName("PSL");
    company.setContactNo(9876543210L);
    company.setDomain("IT");
    when(companyRepository.save(any())).thenReturn(company);
    companyService.updateCompany(new Company());
  }

}
