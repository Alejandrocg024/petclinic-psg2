package org.springframework.samples.petclinic.vet;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;


public interface SpecialtyRepository extends Repository<Specialty, Integer>{


    List<Specialty> findAll() throws DataAccessException;

    Specialty findById(int id) throws DataAccessException;
}
