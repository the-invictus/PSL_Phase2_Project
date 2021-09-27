package com.psl.jun21.grp3.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	User save(User user);

	List<User> getAllUsers();

	void deleteUserById(Long id);
}
