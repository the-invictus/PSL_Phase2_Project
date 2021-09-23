package com.psl.jun21.grp3.applicant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.jun21.grp3.internshipprofile.InternshipProfileRepository;
import com.psl.jun21.grp3.user.UserRepository;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@Autowired
	private InternshipProfileRepository internshipProfileRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicantRepository applicantRepository;

	@ModelAttribute("applicant")
	public ApplicantRegistrationDto userRegistrationDto() {
		return new ApplicantRegistrationDto();
	}

	@GetMapping(path = { "/registration" })
	public String showRegistrationForm() {
		return "applicant-registration";
	}

	@PostMapping(path = { "/registration" })
	public String registerUserAccount(@ModelAttribute("applicant") ApplicantRegistrationDto registrationDto) {
		applicantService.save(registrationDto);
		return "redirect:/applicant/registration?success";
	}

	@GetMapping("/home")
	public String homePage(Model model) {
		model.addAttribute("jobs", internshipProfileRepository.findAll());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("applicant", userRepository.findByEmail(user.getUsername()).getApplicant());
		return "applicant-home";
	}

	@GetMapping("/profile")
	public String profilePage(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("applicant", userRepository.findByEmail(user.getUsername()).getApplicant());
		return "applicant-profile";
	}

}
