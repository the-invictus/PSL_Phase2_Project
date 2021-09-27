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
import com.psl.jun21.grp3.applicant.ApplicantService;
import com.psl.jun21.grp3.company.Company;
import com.psl.jun21.grp3.company.CompanyService;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRepository;
import net.bytebuddy.asm.Advice.Return;

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

  @Autowired
  ApplicantService applicantService;

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

  @RequestMapping(path = {"/edit", "/edit/{id}"})
  public String editInternshipProfileById(Model model, @PathVariable("id") Optional<Long> id)
      throws RecordNotFoundException {
    if (id.isPresent()) {
      InternshipProfile entity = service.getInternshipProfileById(id.get());
      if (!entity.getCompany().getUser().getEmail().equals(getPrincipalUser()))
        return "error";
      model.addAttribute("internshipProfile", entity);
    } else {
      model.addAttribute("internshipProfile", new InternshipProfile());
    }
    return "create-edit-internshipProfile";
  }

  @DeleteMapping("/{id}")
  public String deleteInternshipProfileById(Model model, @PathVariable("id") Long id)
      throws RecordNotFoundException {
    service.deleteInternshipProfileById(id);
    return "redirect:/internshipProfile";
  }

  @RequestMapping(path = "/delete/{id}")
  public String deleteEmployeeById(Model model, @PathVariable("id") Long id)
      throws RecordNotFoundException {
    String companyEmail = service.getInternshipProfileById(id).getCompany().getUser().getEmail();
    if (getPrincipalUser().equals(companyEmail))
      service.deleteInternshipProfileById(id);
    return "redirect:/company/home";
  }

  @PostMapping()
  public String createOrUpdateInternshipProfile(InternshipProfile internshipProfile) {
    User u = userRepo.findByEmail(getPrincipalUser());
    internshipProfile.setCompany(u.getCompany());
    service.createOrUpdateInternshipProfile(internshipProfile);
    return "redirect:/company/home";
  }

  @GetMapping(path = "/{id}/applications")
  public String applicationsByProfileId(Model model, @PathVariable("id") Long id)
      throws RecordNotFoundException {
    String companyEmail = service.getInternshipProfileById(id).getCompany().getUser().getEmail();
    if (!getPrincipalUser().equals(companyEmail))
      return "error";
    List<Object[]> applications = applicantService.getApplicationsByProfileId(id);
    if (applications.size() > 0) {
      model.addAttribute("profileApplications", applications);
    } else {
      model.addAttribute("profileApplications", null);
    }
    return "company-appliedApplications";
  }



}
