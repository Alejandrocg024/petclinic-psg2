package org.springframework.samples.petclinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecialtyService {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Transactional(readOnly = true)	
	public List<Specialty> findSpecialties() throws DataAccessException {
		return specialtyRepository.findAll();
	}

    @Transactional(readOnly = true)
    public Specialty findById(Integer id){
        return this.specialtyRepository.findById(id);
    }	


    
}
