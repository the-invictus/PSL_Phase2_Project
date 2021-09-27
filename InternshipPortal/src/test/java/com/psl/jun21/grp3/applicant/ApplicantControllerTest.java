package com.psl.jun21.grp3.applicant;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

class ApplicantControllerTest {

  @MockBean
  private ApplicantService applicantService;
  private ApplicantController applicantController;

  @MockBean
  Applicant applicant;

  @SuppressWarnings("deprecation")
  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    applicantController = new ApplicantController(applicantService);
  }

  @Test
  void test1() {
    assertThat(applicantController.showRegistrationForm()).isEqualTo("applicant-registration");
  }

}
