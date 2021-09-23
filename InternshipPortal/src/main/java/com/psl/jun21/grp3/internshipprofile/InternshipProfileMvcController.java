package com.psl.jun21.grp3.internshipprofile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rushikesh
 *
 */

@Controller
@RequestMapping("/internshipProfile")
public class InternshipProfileMvcController {

	@Autowired
	InternshipProfileService service;

	@GetMapping
	public String getAllInternshipProfile(Model model) {
		List<InternshipProfile> list = service.getAllInternshipProfiles();
		model.addAttribute("internshipProfiles", list);
		return "list-internshipProfiles";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editInternshipProfileById(Model model, @PathVariable("id") Optional<Long> id)
			throws RecordNotFoundException {
		if (id.isPresent()) {
			InternshipProfile entity = service.getInternshipProfileById(id.get());
			model.addAttribute("internshipProfile", entity);
		} else {
			model.addAttribute("internshipProfile", new InternshipProfile());
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

	@PostMapping
	public String createOrUpdateInternshipProfile(InternshipProfile internshipProfile) {
		service.createOrUpdateInternshipProfile(internshipProfile);
		return "redirect:/internshipProfile";
	}
}
