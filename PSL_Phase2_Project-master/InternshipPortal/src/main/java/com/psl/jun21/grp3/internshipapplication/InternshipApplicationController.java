package com.psl.jun21.grp3.internshipapplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.jun21.grp3.company.CompanyService;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileAppli;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileService;
import com.psl.jun21.grp3.user.UserRepository;

@Controller
@RequestMapping("/internshipApplication")
public class InternshipApplicationController {
	@Autowired
	InternshipProfileService service;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CompanyService comser;
	@GetMapping
	public String getAllInternshipProfile(Model model) {
		List<InternshipProfileAppli> list = service.getAllInternshipProfiles();
		model.addAttribute("internshipProfiles", list);
		return "index";
	}

}
