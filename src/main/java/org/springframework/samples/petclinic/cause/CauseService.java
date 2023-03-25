package org.springframework.samples.petclinic.cause;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.cause.exceptions.ReachedBudgetTargetException;
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
	@Transactional
	public void saveDonation(Donation d) throws DataAccessException, ReachedBudgetTargetException{
		if(donationRepository.sumDonationsCause(d.getCause().getId()) + d.getAmount()> d.getCause().getBudgetTarget()){
            throw new ReachedBudgetTargetException();
        } else if(d.getCause().getActive()==false){
            throw new ReachedBudgetTargetException();
		}
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
