package com.psl.jun21.grp3.internshipprofile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.psl.jun21.grp3.company.Company;

@ExtendWith(MockitoExtension.class)
class InternshipProfileServiceTest {

  @InjectMocks
  InternshipProfileService internshipProfileService;

  @Mock
  InternshipProfileRepository internshipProfileRepository;

  @Test
  void testGetCompanyInternshipProfiles() {
    List<InternshipProfile> profiles =
        new ArrayList<InternshipProfile>(Arrays.asList(new InternshipProfile()));
    when(internshipProfileRepository.findByCompany(any())).thenReturn(profiles);
    assertThat(internshipProfileService.getCompanyInternshipProfiles(new Company()).size())
        .isEqualTo(1);
  }

  @Test
  void testGetInternshipProfileById() throws RecordNotFoundException {
    Optional<InternshipProfile> profile2 =
        Optional.of(new InternshipProfile(1L, "Title", "", "", 2, new Company(), null));
    when(internshipProfileRepository.findById(1L)).thenReturn(profile2);
    assertThat(internshipProfileService.getInternshipProfileById(1L).getId()).isEqualTo(1L);

  }

  @Test
  void testCreateOrUpdateInternshipProfile() {
    InternshipProfile profile = new InternshipProfile();
    profile.setDomain("IT");
    profile.setDuration(2);
    profile.setTitle("Title");
    when(internshipProfileRepository.save(any())).thenReturn(profile);
    assertThat(internshipProfileService.createOrUpdateInternshipProfile(profile).getDomain())
        .isEqualTo("IT");
    assertThat(internshipProfileService.createOrUpdateInternshipProfile(profile).getDuration())
        .isEqualTo(2);
    assertThat(internshipProfileService.createOrUpdateInternshipProfile(profile).getTitle())
        .isEqualTo("Title");

  }

  @Test
  void testDeleteInternshipProfileById() throws RecordNotFoundException {
    when(internshipProfileRepository.findById(anyLong()))
        .thenReturn(Optional.of(new InternshipProfile()));
    internshipProfileService.deleteInternshipProfileById(2L);
  }

}
