package com.psl.jun21.grp3.applicant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.jun21.grp3.internshipapplication.InternshipApplicationRepository;
import com.psl.jun21.grp3.internshipapplication.InternshipApplicationService;
import com.psl.jun21.grp3.internshipprofile.InternshipProfile;
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

	@Autowired
	private InternshipApplicationService internshipApplicationService;
	
	public ApplicantController(ApplicantService applicantService2) {
		
	}

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
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Applicant applicant = userRepository.findByEmail(user.getUsername()).getApplicant();
		model.addAttribute("applicant", applicant);
		model.addAttribute("jobs", internshipProfileRepository.getApplicableProfilesByApplicantId(applicant.getId()));
		return "applicant-home";
	}

	@GetMapping("/profile")
	public String profilePage(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("applicant", userRepository.findByEmail(user.getUsername()).getApplicant());
		return "applicant-profile";
	}
	
	@GetMapping("/history")
	public String historyPage(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Applicant applicant = userRepository.findByEmail(user.getUsername()).getApplicant();
		List<Object[]> obj = internshipProfileRepository.getProfilesByApplicantId(applicant.getId());
		System.out.println(obj.get(0).toString());
		model.addAttribute("profiles", internshipProfileRepository.getProfilesByApplicantId(applicant.getId()));
		return "applicant-history";
	}

	@PutMapping
	public Applicant updateApplicant(@RequestBody Applicant applicant) {
		return applicantService.updateApplicant(applicant);
	}

	@DeleteMapping("/{id}")
	public long removeApplicant(@PathVariable long id) {
		return applicantService.deleteApplicantById(id);
	}
}
