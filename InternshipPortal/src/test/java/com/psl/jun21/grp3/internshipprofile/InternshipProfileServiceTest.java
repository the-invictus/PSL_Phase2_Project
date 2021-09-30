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
import com.psl.jun21.grp3.user.User;

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
    InternshipProfile profileSec = new InternshipProfile();
    profileSec.setId(2L);
    when(internshipProfileRepository.findById(2L)).thenReturn(Optional.of(new InternshipProfile()));
    when(internshipProfileRepository.save(any())).thenReturn(profileSec, profile);
    assertThat(internshipProfileService.createOrUpdateInternshipProfile(profileSec).getId())
        .isEqualTo(2L);
    assertThat(internshipProfileService.createOrUpdateInternshipProfile(profile).getDomain())
        .isEqualTo("IT");
  }

  @Test
  void testDeleteInternshipProfileById() throws RecordNotFoundException {
    when(internshipProfileRepository.findById(anyLong()))
        .thenReturn(Optional.of(new InternshipProfile()));
    internshipProfileService.deleteInternshipProfileById(2L);
  }

  @Test
  void testGetAllInternshipProfiles() {
    Iterable<InternshipProfile> iterable = new Iterable<InternshipProfile>() {
      @Override
      public Iterator<InternshipProfile> iterator() {
        List<InternshipProfile> users = new ArrayList<InternshipProfile>(
            Arrays.asList(new InternshipProfile(), new InternshipProfile()));
        return users.iterator();
      }
    };
    Iterable<InternshipProfile> iterableSec = new Iterable<InternshipProfile>() {
      @Override
      public Iterator<InternshipProfile> iterator() {
        return (new ArrayList<InternshipProfile>()).iterator();
      }
    };
    when(internshipProfileRepository.findAll()).thenReturn(iterable, iterableSec);
    assertThat(internshipProfileService.getAllInternshipProfiles().size()).isEqualTo(2);
    assertThat(internshipProfileService.getAllInternshipProfiles().size()).isEqualTo(0);

  }

}
