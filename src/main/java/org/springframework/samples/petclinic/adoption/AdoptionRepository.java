package org.springframework.samples.petclinic.adoption;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends CrudRepository<Adoption, Integer>{

    List<Adoption> findAll();

    Adoption findById(int id) throws DataAccessException;

    @Query("SELECT a FROM Adoption a WHERE a.pet IN :pets")
    List<Adoption> findByPets(@Param("pets") List<Pet> pets);

    
}
