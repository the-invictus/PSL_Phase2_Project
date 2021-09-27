package com.psl.jun21.grp3.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username and password");
		}

		Collection<UserRole> userRoles = new ArrayList<UserRole>(Arrays.asList(user.getRole()));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(userRoles));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		for (User user : userRepository.findAll())
			list.add(user);
		return list;
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

}
