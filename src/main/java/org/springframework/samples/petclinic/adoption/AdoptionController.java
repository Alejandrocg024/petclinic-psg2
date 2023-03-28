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
package org.springframework.samples.petclinic.adoption;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
@RequestMapping("/adoptions")
public class AdoptionController {

	private static final String ADOPTIONS_LISTING = "adoption/adoptionList";
	private static final String ADOPTIONS_CREATE_FORM = "adoption/createAdoptionForm";

	private final AdoptionService adoptionService;
	private final PetService petService;
	private final OwnerService ownerService;

	@Autowired
	public AdoptionController(AdoptionService adoptionService, PetService petService, OwnerService ownerService) {
		this.adoptionService = adoptionService;
		this.petService= petService;
		this.ownerService= ownerService;
	}

	@GetMapping()
	public ModelAndView showAdopciones(Principal principal) {
		ModelAndView result = new ModelAndView(ADOPTIONS_LISTING);
		result.addObject("nombreUsuario", principal.getName());
		result.addObject("petsInAdoption", petService.findPetsInAdoption());
		return result;
	}
	
	@GetMapping(value = "/{petId}/new")
	public ModelAndView initCreationForm(@PathVariable("petId") Integer petId, Principal principal) {
		ModelAndView mav = new ModelAndView(ADOPTIONS_CREATE_FORM);
		Adoption adoption = new Adoption();
		adoption.setPet(petService.findPetById(petId));
		adoption.setOwner(ownerService.findOwnerByUserName(principal.getName()));
		mav.addObject("adoption", adoption);
		mav.addObject("pet", petService.findPetById(petId));
		return mav;
	}

	@PostMapping(value = "/{petId}/new")
	public ModelAndView processCreationForm(Adoption adoption, BindingResult result, @PathVariable("petId") int petId, Principal principal) {
		ModelAndView mav = new ModelAndView(ADOPTIONS_CREATE_FORM);
		adoption.setPet(petService.findPetById(petId));
		adoption.setOwner(ownerService.findOwnerByUserName(principal.getName()));
		mav.addObject("pet", petService.findPetById(petId));
		if (result.hasErrors()) {
			return mav;
		} else if(adoption.getDescription().length() >= 500){
            mav.addObject("Mensaje", "La descripcion debe ser menor de 500 caracteres");
            return mav;
        } else {
			adoption.setPosting_date(LocalDate.now());
			this.adoptionService.save(adoption);
			return new ModelAndView("redirect:/adoptions");
		}
	}

	@GetMapping(value = "/{ownerId}/accept/{adoptionId}")
	public ModelAndView acceptAdoption(@PathVariable("adoptionId") Integer adoptionId, @PathVariable("ownerId") Integer ownerId) throws DataAccessException, DuplicatedPetNameException {
		Adoption adoptionToUpdate=this.adoptionService.findAdoptionById(adoptionId);
		adoptionToUpdate.setStatus(true);
		this.adoptionService.save(adoptionToUpdate); 
		Pet petToUpdate = adoptionToUpdate.getPet();
		petToUpdate.setOwner(adoptionToUpdate.getOwner());
		petToUpdate.setInAdoption(false);
		this.petService.savePet(petToUpdate);
		return new ModelAndView("redirect:/owners/{ownerId}");
	}

	@PostMapping(value = "/decline/{adoptionId}")
	public ModelAndView declineAdoption(@PathVariable("adoptionId") Integer adoptionId) {
		Adoption adoptionToUpdate=this.adoptionService.findAdoptionById(adoptionId);
		adoptionToUpdate.setStatus(false);
		this.adoptionService.save(adoptionToUpdate); 
		return new ModelAndView("redirect:/owners/{ownerId}");
	}

}
