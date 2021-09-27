package com.psl.jun21.grp3.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NameTest {

	@Test
	void testNameStringStringString() {
		Name name = new Name("First", "Middle", "Last");
		assertThat(name.getFirstName()).isEqualTo("First");
	}

	@Test
	void testToString() {
		Name name = new Name("First", "Middle", "Last");
		assertThat(name.toString()).isEqualTo("Name(firstName=First, middleName=Middle, surname=Last)");

	}

	@Test
	void testInit() {
		Name name = new Name();
		name.setFirstName("First");
		assertThat(name.getFirstName()).isEqualTo("First");

	}
}
