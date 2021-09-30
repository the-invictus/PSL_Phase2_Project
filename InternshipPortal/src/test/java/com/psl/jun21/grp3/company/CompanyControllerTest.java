package com.psl.jun21.grp3.company;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileService;
import com.psl.jun21.grp3.user.UserRepository;
import com.psl.jun21.grp3.user.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  InternshipProfileService service;

  @MockBean
  UserRepository userRepo;

  @MockBean
  CompanyService companyService;

  @MockBean
  UserService userService;

  // @Test
  // void testUserRegistrationDto() {
  // fail("Not yet implemented");
  // }

  @Test
  void testShowRegistrationForm() throws Exception {
    mockMvc.perform(get("/company/registration")).andExpect(status().isOk());
  }

  // @Test
  // void testRegisterUserAccount() {
  // fail("Not yet implemented");
  // }
  //
  // @Test
  // void testUpdateCompany() {
  // fail("Not yet implemented");
  // }
  //
  // @Test
  // void testGetAllInternshipProfile() {
  // fail("Not yet implemented");
  // }
  //
  // @Test
  // void testGetProfile() {
  // fail("Not yet implemented");
  // }

}
