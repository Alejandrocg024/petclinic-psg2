package org.springframework.samples.petclinic.cause;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.cause.exceptions.DonationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CauseService {

	private CauseRepository causeRepository;

	private DonationRepository donationRepository;
	
	@Autowired
	public CauseService(CauseRepository causeRepository, DonationRepository donationRepository) {
		this.causeRepository = causeRepository;
		this.donationRepository = donationRepository;
	}

	@Transactional(readOnly = true)
	public Cause getCauseById(Integer id){
		return causeRepository.findCauseById(id);
	}
	
	@Transactional
	public void saveCause(Cause c) throws DataAccessException{
		causeRepository.save(c);
	}
	
	@Transactional(readOnly = true)
	public List<Cause> getCauses(){
		return causeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Double getSumDonationsCause(Integer id){
		return donationRepository.sumDonationsCause(id);
	}

	@Transactional
	public void saveDonation(Donation d) throws DataAccessException, DonationException{
		Double doub = donationRepository.sumDonationsCause(d.getCause().getId());
		if(doub == null){
			doub = 0.0;
		}
		if(doub + d.getAmount()> d.getCause().getBudgetTarget()){
            throw new DonationException();
        }
		if(d.getAmount()<=0){
			throw new DonationException();
		}
		donationRepository.save(d);
	}
}
