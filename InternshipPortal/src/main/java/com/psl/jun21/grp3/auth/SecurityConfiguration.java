package com.psl.jun21.grp3.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRole;
import com.psl.jun21.grp3.user.UserService;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:config.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${admin_email}")
	private String adminEmail;

	@Value("${admin_password}")
	private String adminPassword;

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationSuccessHandler authHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		try {
			userService.save(
					new User(adminEmail, UserRole.SYSTEM_ADMIN, new BCryptPasswordEncoder().encode(adminPassword)));
		} catch (Exception ignore) {

		}
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").hasAuthority("SYSTEM_ADMIN")
			.antMatchers("/applicant/registration", "/company/registration", "/", "/h2-console/*")
			.permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").successHandler(authHandler).permitAll()
			.and().logout().invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
			.permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

}
