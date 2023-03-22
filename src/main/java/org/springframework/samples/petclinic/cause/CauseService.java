package org.springframework.samples.petclinic.cause;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CauseService {

	private CauseRepository causeRepository;

	private DonationRepository donationRepository;
	
	@Autowired
	public CauseService(CauseRepository causeRepository) {
		this.causeRepository = causeRepository;
	}

	@Transactional(readOnly = true)
	public Cause getCauseById(Integer id){
		return causeRepository.findCauseById(id);
	}
	
	@Transactional
	public void saveCause(Cause c) throws DataAccessException{
		causeRepository.save(c);
	}
	@Transactional
	public void saveDonation(Donation d) throws DataAccessException{
		donationRepository.save(d);
	}
	
	@Transactional(readOnly = true)
	public List<Cause> getCauses(){
		return causeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Double getSumDonationsCause(Integer id){
		return donationRepository.sumDonationsCause(id);
	}
	@Transactional(readOnly = true)
	public List<Donation> getDonationsCauseById(Integer id){
		return donationRepository.donationsCauseById(id);
	}

	

	
}
