package com.psl.jun21.grp3.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@ModelAttribute("company")
	public CompanyRegistrationDto companyRegistrationDto() {
		return new CompanyRegistrationDto();
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
		
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "registration";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@PostMapping("/registration")
	public String registerCompanyAccount(@ModelAttribute("company") CompanyRegistrationDto registrationDto) {
		companyService.save(registrationDto);
		return "redirect:/registration?success";
	}
	

}
