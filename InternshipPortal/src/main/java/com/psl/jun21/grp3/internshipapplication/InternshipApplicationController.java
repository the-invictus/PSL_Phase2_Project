package com.psl.jun21.grp3.internshipapplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.jun21.grp3.company.CompanyService;
import com.psl.jun21.grp3.internshipprofile.InternshipProfile;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileService;
import com.psl.jun21.grp3.user.UserRepository;

@Controller
@RequestMapping("/internshipApplication")
public class InternshipApplicationController {

  @Autowired
  InternshipProfileService service;

  @Autowired
  InternshipApplicationService internshipApplicationService;

  String getPrincipalUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = "";
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }
    return username;
  }

  @GetMapping
  public String getAllInternshipProfile(Model model) {
    List<InternshipProfile> list = service.getAllInternshipProfiles();
    model.addAttribute("internshipProfiles", list);
    return "index";
  }

  @GetMapping("/apply/{profileId}/{applicantId}")
  public String applyForInternship(@PathVariable long profileId, @PathVariable long applicantId) {
    internshipApplicationService.apply(profileId, applicantId);
    return "redirect:/applicant/home?applySuccess";
  }

  @GetMapping("/accept/{id}")
  public String acceptApplication(@PathVariable long id) {
    InternshipApplication application = internshipApplicationService.getApplicationById(id);
    String companyEmail = application.getInternshipProfile().getCompany().getUser().getEmail();
    if (companyEmail.equals(getPrincipalUser())) {
      application.setApplicationStatus(ApplicationStatus.APPROVED);
      internshipApplicationService.save(application);
      return "redirect:/internshipProfile/" + application.getInternshipProfile().getId()
          + "/applications";
    }
    return "error";
  }

  @GetMapping("/reject/{id}")
  public String rejectApplication(@PathVariable long id) {
    InternshipApplication application = internshipApplicationService.getApplicationById(id);
    String companyEmail = application.getInternshipProfile().getCompany().getUser().getEmail();
    if (companyEmail.equals(getPrincipalUser())) {
      application.setApplicationStatus(ApplicationStatus.REJECTED);
      internshipApplicationService.save(application);
      return "redirect:/internshipProfile/" + application.getInternshipProfile().getId()
          + "/applications";
    }
    return "error";
  }

}
