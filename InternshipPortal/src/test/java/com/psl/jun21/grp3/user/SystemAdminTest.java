package com.psl.jun21.grp3.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SystemAdminTest {

	@Test
	public void testToString() {
		SystemAdmin admin = new SystemAdmin();
		assertThat(admin.toString()).isSameAs("SystemAdmin()");
	}

}
