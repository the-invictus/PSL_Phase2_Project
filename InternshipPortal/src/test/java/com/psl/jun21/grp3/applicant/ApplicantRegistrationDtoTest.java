package com.psl.jun21.grp3.applicant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ApplicantRegistrationDtoTest {

  private static ApplicantRegistrationDto applicantRegistrationDto;

  @BeforeAll
  public static void setUp() {
    applicantRegistrationDto = new ApplicantRegistrationDto();
  }

  @Test
  void testSetEmail() {
    applicantRegistrationDto.setEmail("mail@mail.com");
    assertThat(applicantRegistrationDto.getEmail()).isEqualTo("mail@mail.com");
  }

  @Test
  void testSetFirstName() {
    applicantRegistrationDto.setFirstName("First");
    assertThat(applicantRegistrationDto.getFirstName()).isEqualTo("First");
  }

  @Test
  void testSetMiddleName() {
    applicantRegistrationDto.setMiddleName("M");
    assertThat(applicantRegistrationDto.getMiddleName()).isEqualTo("M");
  }

  @Test
  void testSetSurname() {
    applicantRegistrationDto.setSurname("S");
    assertThat(applicantRegistrationDto.getSurname()).isEqualTo("S");
  }

  @Test
  void testSetPassword() {
    applicantRegistrationDto.setPassword("P");
    assertThat(applicantRegistrationDto.getPassword()).isEqualTo("P");
  }

  @Test
  void testSetContactNo() {
    applicantRegistrationDto.setContactNo("2872367");
    assertThat(applicantRegistrationDto.getContactNo()).isEqualTo("2872367");
  }

  @Test
  void testSetDegree() {
    applicantRegistrationDto.setDegree("BE");
    assertThat(applicantRegistrationDto.getDegree()).isEqualTo("BE");
  }

  @Test
  void testApplicantRegistrationDto() {
    ApplicantRegistrationDto dto = new ApplicantRegistrationDto();
    assertThat(dto.getClass()).isEqualTo(ApplicantRegistrationDto.class);
  }

  @Test
  void testToString() {
    assertThat(applicantRegistrationDto.toString()).isEqualTo(
        "ApplicantRegistrationDto(email=null, firstName=First, middleName=null, surname=null, password=P, contactNo=2872367, specialization=null, degree=null)");
  }

}
