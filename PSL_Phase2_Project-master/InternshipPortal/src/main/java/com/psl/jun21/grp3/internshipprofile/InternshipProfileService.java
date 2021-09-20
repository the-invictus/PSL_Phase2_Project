package com.psl.jun21.grp3.internshipprofile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rushikesh
 *
 */
@Service
public class InternshipProfileService {

	@Autowired
	InternshipProfileRepository repository;

	public List<InternshipProfileAppli> getAllInternshipProfiles() {
		List<InternshipProfileAppli> result = (List<InternshipProfileAppli>) repository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<InternshipProfileAppli>();
		}
	}

	public InternshipProfileAppli getInternshipProfileById(Long id) throws RecordNotFoundException {
		Optional<InternshipProfileAppli> internshipProfile = repository.findById(id);

		if (internshipProfile.isPresent()) {
			return internshipProfile.get();
		} else {
			throw new RecordNotFoundException("No internshipProfile record exist for given id");
		}
	}

	public InternshipProfileAppli createOrUpdateInternshipProfile(InternshipProfileAppli entity) {
		if (entity.getId() == null) {
			entity = repository.save(entity);

			return entity;
		} else {
			Optional<InternshipProfileAppli> internshipProfile = repository.findById(entity.getId());

			if (internshipProfile.isPresent()) {
				InternshipProfileAppli newEntity = internshipProfile.get();

				newEntity.setTitle(entity.getTitle());
				newEntity.setDuration(entity.getDuration());
				newEntity.setDescription(entity.getDescription());
				newEntity.setDomain(entity.getDomain());
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void deleteInternshipProfileById(Long id) throws RecordNotFoundException {
		Optional<InternshipProfileAppli> internshipProfile = repository.findById(id);

		if (internshipProfile.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No internshipProfile record exist for given id");
		}
	}
}