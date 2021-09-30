package com.psl.jun21.grp3.applicant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.psl.jun21.grp3.user.UserRepository;
import com.psl.jun21.grp3.user.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class ApplicantControllerTest {

  @InjectMocks
  private ApplicantService applicantService;

  @Mock
  ApplicantRepository applicantRepository;

  @Mock
  private BCryptPasswordEncoder passwordEncoder;
  @Mock
  private UserRepository userRepository;

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
  void testShowRegistrationForm() {
    assertThat(applicantController.showRegistrationForm()).isEqualTo("applicant-registration");
  }

  @Test
  void testUserRegistrationDto() {
    assertThat(applicantController.userRegistrationDto().getClass())
        .isEqualTo(ApplicantRegistrationDto.class);
  }

}
