package com.psl.jun21.grp3.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.psl.jun21.grp3.internshipprofile.RecordNotFoundException;
import com.psl.jun21.grp3.user.UserService;


@Controller
public class HomeController {
	
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String homePage() {
		return "home";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/admin")
	public String adminPage(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "admin";
	}
	
	@GetMapping("/admin/user/delete/{id}")
	  public String deleteUserById(Model model, @PathVariable("id") Long id){
		userService.deleteUserById(id);
	    return "redirect:/admin";
	  }
}
