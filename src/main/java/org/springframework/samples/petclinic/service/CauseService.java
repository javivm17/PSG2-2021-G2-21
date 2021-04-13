package org.springframework.samples.petclinic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.repository.CauseRepository;

@Service
public class CauseService {

	private final CauseRepository causeRepo;
	
	@Autowired
	public CauseService(final CauseRepository causeRepo) {
		this.causeRepo = causeRepo;
	}
	
	@Transactional
	public Iterable<Cause> findAll() {
		return causeRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public  Optional<Cause> findCauseById(int id){ 
		return causeRepo.findById(id);
	}
	
	@Transactional
	public  void save(Cause cause) {
		causeRepo.save(cause);
	}

	public  void delete(Cause cause) { 
		causeRepo.delete(cause);
	}
	
	public Integer getCurrentBudget(int causeId) {
		return causeRepo.getCurrentBudget(causeId);
	}
}
