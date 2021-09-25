package com.psl.training.Employee_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.psl.training.Employee_Management.model.Company;
import com.psl.training.Employee_Management.service.ApplicantService;
import com.psl.training.Employee_Management.service.CompanyService;
import com.psl.training.Employee_Management.service.InternshipJobService;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	 @Autowired
	private InternshipJobService internshipjobservice;
	 
	 @Autowired
	 private ApplicantService applicantservice;
	 
	public CompanyController(CompanyService employeeService) {
		super();
		this.companyService = employeeService;
	}
	
	@GetMapping("/companies")
	//model is uses to pass the data to the view
	public String listEMployees(Model model) {
		model.addAttribute("Companies", companyService.getAllDetails());
		//view name is companies
		return "Companies";
		
	}
	//update company
	@GetMapping("/companies/edit/{id}")
	public String editCompanyForm(@PathVariable Integer id, Model model) {
		model.addAttribute("companies", companyService.getCompanyById(id));
		return "update_company";
	}
	
	@PostMapping("companies/{id}")
	public String updateCompany(@PathVariable Integer id, 
			@ModelAttribute("company") Company company, Model model, RedirectAttributes redirAttr ) {
			Company existingCompany = companyService.getCompanyById(id);
			existingCompany.setCompany_id(id);
			existingCompany.setName(company.getName());
			existingCompany.setDomain(company.getDomain());
			existingCompany.setContact_no(company.getContact_no());
			companyService.updateCompany(existingCompany);
			redirAttr.addFlashAttribute("success", "Updated Successfully");
		    return "redirect:/home";
		    
	}
	
	//delete company
	@GetMapping("companies/{id}")
	public String deleteCompany(@PathVariable Integer id, RedirectAttributes redirAttr ) {
		companyService.deleteCompany(id);
		redirAttr.addFlashAttribute("success", "Deleted Successfully");
		return "redirect:/home";
	}
	@GetMapping("/")
    public String getCompanyById(Model model, @Param("company_id") String id) {
        Company company = companyService.getCompanyById(Integer.parseInt(id));
        model.addAttribute("Companies", company);  
        return "Companies";
    }
	
	@GetMapping("/home")
	public String getHomePage() {
		return "search";
	}
	
	@GetMapping("/test")
	public String getTestPage(Model model, @Param("company_id") String id) {
		Company company = companyService.getCompanyById(Integer.parseInt(id));
        model.addAttribute("companies", company); 
		return "test";
	}
	@GetMapping("/home2")
	public String getSearchPageForInternships() {
		return "searchForinternshipprofiles";
	}
	
	@GetMapping("/internships")
	public String getInternshipListPage(Model model, @Param("company_id") String id) {
		//System.out.println(Integer.parseInt(id));
		//Company company = companyService.getInternshipListFromCompanyById(Integer.parseInt(id));
        model.addAttribute("jobs", internshipjobservice.getInternshipListFromCompanyById(Integer.parseInt(id))); 
		return "Job_List";
	}
	@GetMapping("/candidates/{id}")
	public String getCandidates(Model model, @PathVariable Long id) {
		model.addAttribute("applicants",applicantservice.getApplicantsByjobId(id));
		return "Candidates";
	}
     
}
