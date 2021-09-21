package com.psl.jun21.grp3.internshipprofile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.jun21.grp3.company.Company;
import com.psl.jun21.grp3.company.CompanyService;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRepository;

/**
 * @author Rushikesh
 *
 */

@Controller
@RequestMapping("/internshipProfile")
public class InternshipProfileMvcController {

	@Autowired
	InternshipProfileService service;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CompanyService comser;

	@GetMapping
	public String getAllInternshipProfile(Model model) {
		/*
		List<InternshipProfileAppli> list = service.getAllInternshipProfiles();
		model.addAttribute("internshipProfiles", list);
		return "list-internshipProfiles";
		
		*/
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		
		User u=userRepo.findByEmail(username);
		Company c=comser.getCompanyDetails(u.getId());
		List<InternshipProfileAppli> list = service.getCompanyInternshipProfiles(c);
		model.addAttribute("internshipProfiles", list);
		return "list-internshipProfiles";
		
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editInternshipProfileById(Model model, @PathVariable("id") Optional<Long> id)
			throws RecordNotFoundException {
		if (id.isPresent()) {
			InternshipProfileAppli entity = service.getInternshipProfileById(id.get());
			model.addAttribute("internshipProfile", entity);
		} else {
			model.addAttribute("internshipProfile", new InternshipProfileAppli());
		}
		return "create-edit-internshipProfile";
	}

	@DeleteMapping("/{id}")
	public String deleteInternshipProfileById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteInternshipProfileById(id);
		return "redirect:/internshipProfile";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteInternshipProfileById(id);
		return "redirect:/internshipProfile";
	}

	@PostMapping()
	public String createOrUpdateInternshipProfile(InternshipProfileAppli internshipProfile) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		User u=userRepo.findByEmail(username);
		Company c=comser.getCompanyDetails(u.getId());
		internshipProfile.setCompany(c);
		service.createOrUpdateInternshipProfile(internshipProfile);
		return "redirect:/internshipProfile";
	}
}
