package com.psl.jun21.grp3.applicant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class ApplicantServiceTest {

  @InjectMocks
  ApplicantService service;

  @Mock
  ApplicantRepository repository;

  @Mock
  BCryptPasswordEncoder passwordEncoder;

  @Mock
  UserRepository userRepository;

  @Test
  void testSave() {
    when(userRepository.save(any())).thenReturn(new User());
    when(passwordEncoder.encode("")).thenReturn("");
    ApplicantRegistrationDto dto =
        new ApplicantRegistrationDto("mail@mail.com", "", "", "", "", "1", "", "");
    assertThat(service.save(dto)).isEqualTo(null);
  }

  @Test
  void testFindApplicantById() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(new Applicant()));
    assertThat(service.findApplicantById(0)).isNotNull();
  }

  @Test
  void testDeleteApplicantById() {
    repository.deleteById(1L);
    assertThat(service.deleteApplicantById(1L)).isEqualTo(1L);

  }

  @Test
  void testUpdateApplicant() {
    Applicant applicant = new Applicant();
    applicant.setContactNo(0L);
    applicant.setDegree("BE");
    when(repository.save(applicant)).thenReturn(applicant);
    assertThat(service.updateApplicant(applicant).getDegree()).isEqualTo("BE");
  }

  @Test
  void testGetApplicantsByProfileId() {
    when(repository.getApplicantsByProfileId(0L)).thenReturn(new ArrayList<Applicant>());
    assertThat(service.getApplicantsByProfileId(0).size()).isEqualTo(0);
  }

  @Test
  void testGetApplicationsByProfileId() {
    when(repository.getApplicationsByProfileId(0)).thenReturn(new ArrayList<Object[]>());
    assertThat(service.getApplicationsByProfileId(0).size()).isEqualTo(0);
  }

}
