package com.psl.jun21.grp3.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	User save(User user);
}
