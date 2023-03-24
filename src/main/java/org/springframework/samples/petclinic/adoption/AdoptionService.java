package org.springframework.samples.petclinic.adoption;

import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray.ReadOnly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService {

    private AdoptionRepository adoptionRepository;

    @Autowired
    public AdoptionService(AdoptionRepository adoptionRepository){
        this.adoptionRepository = adoptionRepository;
    }
    
    @Transactional
    public void save(Adoption a) throws DataAccessException{
         adoptionRepository.save(a);
    }

    @Transactional(readOnly = true)
    public List<Adoption> getAdoptions(){
        return adoptionRepository.findAll();
    }

    @Transactional(readOnly = true)
	public Adoption findAdoptionById(int id) throws DataAccessException {
		return adoptionRepository.findById(id);
	}

    @Transactional(readOnly = true)
	public List<Adoption> findAdoptionsByPets(List<Pet> pets) throws DataAccessException {
		return adoptionRepository.findByPets(pets);
	}

}
