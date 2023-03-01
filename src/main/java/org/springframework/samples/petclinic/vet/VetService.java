/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class VetService {

	private VetRepository vetRepository;
	private SpecialtyRepository specialtyRepository;

	@Autowired
	public VetService(VetRepository vetRepository , SpecialtyRepository specialtyRepository) {
		this.vetRepository = vetRepository;
		this.specialtyRepository = specialtyRepository;
	}		

	@Transactional(readOnly = true)	
	public Collection<Vet> findVets() throws DataAccessException {
		return vetRepository.findAll();
	}

	@Transactional(readOnly = true)	
    public Vet findVetById(Integer vetId) {
        return vetRepository.findById(vetId);
    }	

	@Transactional(readOnly = true)	
    public List<Specialty> findSpecialties() {
        return specialtyRepository.findAll();
    }

	@Transactional
	public void saveVet(Vet vet) throws DataAccessException {
		vetRepository.save(vet);
	}

	@Transactional(readOnly = true)
	public Specialty findSpecialtyByName(String name) {
		return vetRepository.findSpecialtyByName(name);
	}

	@Transactional(readOnly = true)
	public Specialty findSpecialtyById(Integer id) {
		return vetRepository.findSpecialtyById(id);
	}
}
