package com.psl.jun21.grp3.company;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.psl.jun21.grp3.applicant.ApplicantService;
import com.psl.jun21.grp3.internshipprofile.InternshipProfile;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileService;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRepository;

/**
 * @author Rushikesh Dimuthi
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
  private final Logger log = LoggerFactory.getLogger(CompanyController.class);

  @Autowired
  InternshipProfileService service;
  @Autowired
  UserRepository userRepo;
  @Autowired
  CompanyService comser;

  @Autowired
  private CompanyService companyService;

  @ModelAttribute("company")
  public CompanyRegistrationDto userRegistrationDto() {
    return new CompanyRegistrationDto();
  }

  @GetMapping(path = {"/registration"})
  public String showRegistrationForm() {
    return "company-registration";
  }

  @PostMapping(path = {"/registration"})
  public String registerUserAccount(
      @ModelAttribute("company") CompanyRegistrationDto registrationDto) {
    companyService.save(registrationDto);
    return "redirect:/company/registration?success";
  }

  @PostMapping("/{id}")
  public String updateCompany(@PathVariable long id,
      @ModelAttribute("company") CompanyRegistrationDto company) {
    log.info("Company update request {}", company.toString());
    Company existingCompany = companyService.getCompanyDetails(id);
    existingCompany.setCompanyName(company.getCompanyName());
    existingCompany.setDomain(company.getDomain());
    existingCompany.setContactNo(Long.parseLong(company.getContactNo()));
    companyService.updateCompany(existingCompany);
    return "redirect:/company/home";
  }

  @GetMapping("/home")
  public String getAllInternshipProfile(Model model) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = "";
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }

    User u = userRepo.findByEmail(username);
    List<InternshipProfile> list = service.getCompanyInternshipProfiles(u.getCompany());
    model.addAttribute("internshipProfiles", list);
    return "company-home";
  }

  @GetMapping("/profile")
  public String getProfile(Model model) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = "";
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }
    User u = userRepo.findByEmail(username);
    model.addAttribute("company", u.getCompany());
    return "company-profile";
  }


}
