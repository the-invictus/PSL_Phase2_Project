package com.psl.jun21.grp3.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Test
	public void testSave() {
		User user = new User();
		user.setEmail("user@email.com");
		user.setPassword("pass");
		user.setRole(UserRole.APPLICANT);

		when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
		User created = userService.save(user);
		assertThat(created.getEmail()).isSameAs(user.getEmail());
	}

	@Test
	public void testLoadUserByUsername() {
		User user = new User("user@email.com", UserRole.APPLICANT, "pass");

		when(userRepository.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(null, user);

		assertThatExceptionOfType(UsernameNotFoundException.class)
				.isThrownBy(() -> userService.loadUserByUsername("user@gmail.com"))
				.withMessage("Invalid username and password");

		assertThat(userService.loadUserByUsername("user@email.com").toString()).isEqualTo(
				"org.springframework.security.core.userdetails.User [Username=user@email.com, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, credentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[APPLICANT]]");

	}

	@Test
	public void testGetAllUsers() {
		Iterable<User> iterable = new Iterable<User>() {

			@Override
			public Iterator<User> iterator() {
				List<User> users = new ArrayList<User>(Arrays.asList(new User(), new User()));
				return users.iterator();
			}
		};
		when(userRepository.findAll()).thenReturn(iterable);
		assertThat(userService.getAllUsers().size()).isEqualTo(2);
	}

	@Test
	public void testDeleteById() {
		userService.deleteUserById(1L);
		assertThat(userService.getAllUsers().size()).isEqualTo(0);
	}
}
