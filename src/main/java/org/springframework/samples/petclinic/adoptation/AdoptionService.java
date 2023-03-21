package org.springframework.samples.petclinic.adoptation;

import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray.ReadOnly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
}
