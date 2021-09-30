package com.psl.jun21.grp3.internshipapplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.psl.jun21.grp3.applicant.Applicant;
import com.psl.jun21.grp3.applicant.ApplicantService;
import com.psl.jun21.grp3.internshipprofile.InternshipProfile;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileService;
import com.psl.jun21.grp3.internshipprofile.RecordNotFoundException;

@ExtendWith(MockitoExtension.class)
public class InternshipApplicationServiceImplTest {

  @InjectMocks
  private InternshipApplicationServiceImpl service;

  @Mock
  private InternshipApplicationRepository repository;

  @Mock
  private ApplicantService applicantService;

  @Mock
  private InternshipProfileService internshipProfileService;

  @Mock
  private InternshipApplicationService internshipApplicationService;

  @org.junit.jupiter.api.Test
  public void getApplicationByIdTest() {

    long id = 1;
    Optional<InternshipApplication> i1 = Optional.of(new InternshipApplication());
    i1.get().setInternship_app_id(1);
    i1.get().setApplicationStatus(ApplicationStatus.PENDING);
    when(repository.findById(1L)).thenReturn(i1);
    assertEquals(1L, service.getApplicationById(id).getInternship_app_id());

  }

  @Test
  public void saveTest() {
    InternshipApplication i1 = (new InternshipApplication());
    i1.setApplicationStatus(ApplicationStatus.APPROVED);
    when(repository.save(any())).thenReturn(i1);
    assertThat(service.save(i1).getApplicationStatus()).isEqualTo(ApplicationStatus.APPROVED);
  }

  @Test
  public void applyTest() throws RecordNotFoundException {
    when(applicantService.findApplicantById(0)).thenReturn(new Applicant());
    when(internshipProfileService.getInternshipProfileById(0L)).thenReturn(new InternshipProfile());
    when(internshipApplicationService.save(any())).thenReturn(new InternshipApplication());
    service.apply(0, 0);
  }

}
