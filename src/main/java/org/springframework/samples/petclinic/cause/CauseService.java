package org.springframework.samples.petclinic.cause;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CauseService {

	private CauseRepository causeRepository;
	
	@Autowired
	public CauseService(CauseRepository causeRepository) {
		this.causeRepository = causeRepository;
	}
	
	
	@Transactional
	public void save(Cause c) throws DataAccessException{
		causeRepository.save(c);
	}
	
	@Transactional(readOnly = true)
	public List<Cause> getCauses(){
		return causeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Double getSumDonationsCause(Integer id){
		return causeRepository.sumDonationsCause(id);
	}

	
}
