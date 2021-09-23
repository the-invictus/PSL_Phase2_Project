package com.psl.jun21.grp3.internshipapplication;

import java.util.List;

public interface InternshipApplicationService {
	InternshipApplication save(InternshipApplication internshipApplication);

	void apply(long profileId, long applicantId);

	List<InternshipApplication> getPendingAppByApplicantIdProfileId(long a_id, long p_id);
}
