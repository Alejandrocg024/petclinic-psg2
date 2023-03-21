package org.springframework.samples.petclinic.adoptation;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends CrudRepository<Adoption, Integer>{

    List<Adoption> findAll();
    
}
