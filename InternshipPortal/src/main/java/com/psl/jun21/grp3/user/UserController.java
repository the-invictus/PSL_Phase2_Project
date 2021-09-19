package com.psl.jun21.grp3.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		// TODO
		// System.out.println(registrationDto.toString());
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}

}
