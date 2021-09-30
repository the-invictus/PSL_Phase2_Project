package com.psl.jun21.grp3.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.psl.jun21.grp3.user.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Mock
  UserService userService;

  @Test
  void testHomePage() throws Exception {
    mockMvc.perform(get("/")).andExpect(status().isOk());
  }

  @Test
  void testLoginPage() throws Exception {
    mockMvc.perform(get("/login")).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(authorities = {"SYSTEM_ADMIN"})
  void testAdminPage() throws Exception {
    mockMvc.perform(get("/admin")).andExpect(status().isOk());
    // UserDetails userDetails = new org.springframework.security.core.userdetails.User(
    // "admin@mail.com", "pass", new ArrayList<SimpleGrantedAuthority>(
    // Arrays.asList(new SimpleGrantedAuthority("SYSTEM_ADMIN"))));
    // when(userService.loadUserByUsername("admin@mail.com")).thenReturn(userDetails);
    // RequestBuilder requestBuilder = formLogin("/admin").user("admin@mail.com").password("pass");
    // mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
  }

  @Test
  void testDeleteUserById() throws Exception {
    mockMvc.perform(get("/admin/user/delete/2")).andExpect(status().is(302));
  }
}
