package com.psl.jun21.grp3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.psl.jun21.grp3.applicant.Applicant;
import com.psl.jun21.grp3.applicant.ApplicantController;
import com.psl.jun21.grp3.applicant.ApplicantRegistrationDto;
import com.psl.jun21.grp3.applicant.ApplicantService;

@ExtendWith(SpringExtension.class)
class ApplicantControllerTest {
  /*
   * @Autowired private ApplicantController applicantController;
   * 
   * private ApplicantRegistrationDto applicantRegistrationDto;
   * 
   * @BeforeEach public void setup() { applicantRegistrationDto.setFirstName("Ram");
   * applicantRegistrationDto.setMiddleName("Charan");
   * applicantRegistrationDto.setSurname("Konidela"); applicantRegistrationDto.setPassword("abc12");
   * applicantRegistrationDto.setEmail("abcxy@gmail.com");
   * applicantRegistrationDto.setDegree("B.Tech");
   * applicantRegistrationDto.setContactNo("124567890");
   * applicantRegistrationDto.setSpecialization("AI & ML"); }
   * 
   * @Test void testUserRegistrationDto() { String result =
   * applicantController.registerUserAccount(applicantRegistrationDto);
   * assertEquals("success",result); }
   */

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

  @Test
  void test2() {
    ApplicantRegistrationDto aa = mock(ApplicantRegistrationDto.class);
    Mockito.when(applicantService.save(any())).thenReturn(applicant);
    assertNotNull(applicantController.registerUserAccount(aa));
  }
}
