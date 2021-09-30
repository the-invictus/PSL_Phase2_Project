package com.psl.jun21.grp3.applicant;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.psl.jun21.grp3.internshipapplication.InternshipApplication;
import com.psl.jun21.grp3.user.Name;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRole;

class ApplicantTest {

  private static Applicant applicant;

  @BeforeAll
  public static void setUp() {
    applicant = new Applicant();
  }

  @Test
  void testGetName() {
    applicant.setName(new Name("F", "M", "L"));
    assertThat(applicant.getName().getFirstName()).isEqualTo("F");
  }

  @Test
  void testGetContactNo() {
    applicant.setContactNo(3L);
    assertThat(applicant.getContactNo()).isEqualTo(3L);
  }

  @Test
  void testSetId() {
    applicant.setId(1L);
    assertThat(applicant.getId()).isEqualTo(1L);
  }

  @Test
  void testSetUser() {
    applicant.setUser(new User("mail@mail.com", UserRole.APPLICANT, "pass"));
    assertThat(applicant.getUser().getPassword()).isEqualTo("pass");
  }

  @Test
  void testSetInternshipApplications() {
    applicant.setInternshipApplications(new ArrayList<InternshipApplication>());
    assertThat(applicant.getInternshipApplications().size()).isEqualTo(0);
  }

}
