package com.psl.jun21.grp3.company;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CompanyRegistrationDtoTest {

  static CompanyRegistrationDto dto;

  @BeforeAll
  static void create() {
    dto = new CompanyRegistrationDto();
    dto.setDomain("IT");
    dto.setEmail("mail@domail.com");
  }

  @Test
  void testGetEmail() {
    assertThat(dto.getEmail()).isEqualTo("mail@domail.com");
  }

  @Test
  void testGetDomain() {
    assertThat(dto.getDomain()).isEqualTo("IT");
  }

  @Test
  void testCompanyRegistrationDtoStringStringStringStringString() {
    CompanyRegistrationDto cDto =
        new CompanyRegistrationDto("mail@mail.com", "PSL", "pass", "", "");
    assertThat(cDto.getCompanyName()).isEqualTo("PSL");
  }

  @Test
  void testCompanyRegistrationDto() {
    CompanyRegistrationDto cDto = new CompanyRegistrationDto();
    assertThat(cDto.getClass()).isEqualTo(CompanyRegistrationDto.class);
  }

  @Test
  void testToString() {
    assertThat(dto.toString()).isEqualTo(
        "CompanyRegistrationDto(email=mail@domail.com, companyName=null, password=null, contactNo=null, domain=IT)");
  }

}
