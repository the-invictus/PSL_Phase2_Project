package com.psl.jun21.grp3.internshipapplication;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.jun21.grp3.applicant.ApplicantRepository;
import com.psl.jun21.grp3.applicant.ApplicantService;
import com.psl.jun21.grp3.internshipprofile.InternshipProfileService;
import com.psl.jun21.grp3.internshipprofile.RecordNotFoundException;

@Service
public class InternshipApplicationServiceImpl implements InternshipApplicationService {

  @Autowired
  ApplicantRepository applicantRepository;

  @Autowired
  ApplicantService applicantService;

  @Autowired
  InternshipProfileService internshipProfileService;

  @Autowired
  InternshipApplicationService internshipApplicationService;

  @Autowired
  InternshipApplicationRepository internshipApplicationRepository;

  @Override
  public InternshipApplication save(InternshipApplication internshipApplication) {
    return internshipApplicationRepository.save(internshipApplication);
  }

  @Override
  public void apply(long profileId, long applicantId) {
    InternshipApplication application = new InternshipApplication();
    application.setApplicant(applicantService.findApplicantById(applicantId));
    try {
      application
          .setInternshipProfile(internshipProfileService.getInternshipProfileById(profileId));
    } catch (RecordNotFoundException e) {
      e.printStackTrace();
    }
    application.setAppliedOn(new Date());
    application.setApplicationStatus(ApplicationStatus.PENDING);
    List<InternshipApplication> list = getPendingAppByApplicantIdProfileId(applicantId, profileId);
    // if no previous pending applications
    if (list.size() == 0)
      internshipApplicationService.save(application);
  }

  @Override
  public List<InternshipApplication> getPendingAppByApplicantIdProfileId(long a_id, long p_id) {
    return internshipApplicationRepository.getPendingAppByApplicantIdProfileId(a_id, p_id);
  }

  @Override
  public InternshipApplication getApplicationById(long id) {
    return internshipApplicationRepository.findById(id).get();
  }

}
