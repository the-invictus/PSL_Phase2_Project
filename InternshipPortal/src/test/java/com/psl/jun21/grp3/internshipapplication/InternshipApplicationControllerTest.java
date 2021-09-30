package com.psl.jun21.grp3.internshipapplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.psl.jun21.grp3.internshipprofile.InternshipProfile;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class InternshipApplicationControllerTest {
  @Autowired
  MockMvc mockMvc;

  @InjectMocks
  InternshipApplicationController controller;

  @Mock
  InternshipProfileService service;

  @Mock
  InternshipApplicationService applicationService;

  @Test
  @WithMockUser(authorities = {"APPLICANT"}, username = "applicant@mail.com")
  void testGetPrincipalUser() throws Exception {
    assertThat(controller.getPrincipalUser()).isEqualTo("applicant@mail.com");
  }

  // @Test
  // @WithMockUser(authorities = {"APPLICANT"}, username = "applicant@mail.com", password = "pass")
  // void testGetAllInternshipProfile() throws Exception {
  // when(service.getAllInternshipProfiles()).thenReturn(new ArrayList<InternshipProfile>());
  // mockMvc.perform(get("/internshipApplication")).andExpect(status().isOk())
  // .andExpect(view().name("index"));
  // }

  // @Test
  // @WithMockUser(authorities = {"APPLICANT"}, username = "applicant@mail.com", password = "pass")
  // void testApplyForInternship() throws Exception {
  // // mockMvc.perform(get("/apply/1/2")).andExpect(redirectedUrl(""));
  // }
  //
  // @Test
  // void testAcceptApplication() {
  // fail("Not yet implemented");
  // }
  //
  // @Test
  // void testRejectApplication() {
  // fail("Not yet implemented");
  // }

}
