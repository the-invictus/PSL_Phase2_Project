package com.psl.jun21.grp3.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@ModelAttribute("applicant")
	public ApplicantRegistrationDto userRegistrationDto() {
		return new ApplicantRegistrationDto();
	}

	@GetMapping(path = { "/login" })
	public String loginPage() {
		return "login";
	}

	@GetMapping(path = { "/registration" })
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping(path = { "/registration" })
	public String registerUserAccount(@ModelAttribute("applicant") ApplicantRegistrationDto registrationDto) {
		applicantService.save(registrationDto);
		return "redirect:/applicant/registration?success";
	}

}
