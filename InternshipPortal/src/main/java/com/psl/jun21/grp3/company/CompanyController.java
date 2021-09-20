package com.psl.jun21.grp3.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.jun21.grp3.auth.CompanyRegistrationDto;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@ModelAttribute("company")
	public CompanyRegistrationDto userRegistrationDto() {
		return new CompanyRegistrationDto();
	}

	@GetMapping(path = { "/registration" })
	public String showRegistrationForm() {
		return "company-registration";
	}

	@PostMapping(path = { "/registration" })
	public String registerUserAccount(@ModelAttribute("company") CompanyRegistrationDto registrationDto) {
		companyService.save(registrationDto);
		return "redirect:/company/registration?success";
	}

}
