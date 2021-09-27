package com.psl.jun21.grp3.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void testUserStringUserRoleString() {
		User user = new User("user@mail.com", UserRole.APPLICANT, "pass");
		assertThat(user.getEmail()).isEqualTo("user@mail.com");
	}

	@Test
	void testUser() {
		User user = new User();
		assertThat(user.getEmail()).isEqualTo(null);
	}

	@Test
	void testToString() {
		User user = new User("user@mail.com", UserRole.APPLICANT, "pass");
		assertThat(user.toString()).isEqualTo(
				"User(id=0, email=user@mail.com, role=APPLICANT, password=pass, company=null, applicant=null)");

	}

}
