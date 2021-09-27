package com.psl.jun21.grp3.auth;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;




public class SecurityConfigurationTest {
	
	/**
     * Initialize the application context to re-use in all test cases
     * */
	static ApplicationContext applicationContext = null;
	static JdbcDaoImpl userDetailsService = null;
	@BeforeClass
    public static void setup()
    {
        //Create application context instance
        applicationContext = new ClassPathXmlApplicationContext("application-security.xml");
        //Get user details service configured in configuration 
        userDetailsService = applicationContext.getBean(JdbcDaoImpl.class);
    }
     
    /**
     * Test the valid user with valid role
     * */
    @Test
    public void testValidRole()
    {
        //Get the user by username from configured user details service
        UserDetails userDetails = userDetailsService.loadUserByUsername ("user1");
        Authentication authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        
    }
     
    /**
     * Test the valid user with INVALID role
     * */
   /* @Test (expected = AccessDeniedException.class)
    public void testInvalidRole()
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername ("user1");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthority("ROLE_INVALID"));
        Authentication authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
       }
     
    /**
     * Test the INVALID user 
     * */
    /*@Test (expected = AccessDeniedException.class)
    public void testInvalidUser()
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername ("admin");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthority("ROLE_INVALID"));
        Authentication authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        
    }
     */
     
    
}
