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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private static final String VIEWS_VET_CREATE_OR_UPDATE_FORM = "vets/createOrUpdateVetForm";

	private final VetService vetService;

	private final SpecialtyService specialtyService;

	@Autowired
	public VetController(VetService clinicService, SpecialtyService specialtyService) {
		this.vetService = clinicService;
		this.specialtyService = specialtyService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/vets/new")
	public ModelAndView initCreationForm() {
		ModelAndView mav = new ModelAndView(VIEWS_VET_CREATE_OR_UPDATE_FORM);
		Vet vet = new Vet();
		List<Specialty> specialties = specialtyService.findSpecialties().stream().collect(Collectors.toList());
		mav.addObject("specialties1",specialties);
		mav.addObject("vet", vet);
		return mav;
	}

	@PostMapping(value = "/vets/new")
	public String processCreationForm(@Valid Vet vet, BindingResult result,
	@RequestParam(required = false) Set<Integer> specialties) {
		if (result.hasErrors()) {
			return VIEWS_VET_CREATE_OR_UPDATE_FORM;
		}
		else {
			Set<Specialty> specialtiesUpdated = new HashSet<Specialty>();
			if (specialties != null) {
				for (Integer s: specialties) {
					Specialty newSpecialty = this.vetService.findSpecialtyById(s);
					specialtiesUpdated.add(newSpecialty);
				}
			}
			vet.setSpecialtiesInternal(specialtiesUpdated);
			this.vetService.saveVet(vet);
			return "redirect:/vets/" + vet.getId();
		}
	}

	@GetMapping(value = { "/vets" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}
	@GetMapping("/vets/delete/{vetId}")
	public ModelAndView deleteVet(@PathVariable("vetId") Integer vetId){
		vetService.deleteVet(vetId);
		return new ModelAndView("redirect:/vets");
	}

	@GetMapping("/vets/{vetId}")
	public ModelAndView showVet(@PathVariable("vetId") int vetId) {
		ModelAndView mav = new ModelAndView("vets/vetsDetails");
		mav.addObject("vets",this.vetService.findVetById(vetId));
		return mav;
	}  
	

	@GetMapping(value = "/vets/{vetId}/edit")
	public ModelAndView initUpdateVetForm(@PathVariable("vetId") int vetId) {
		ModelAndView mav = new ModelAndView(VIEWS_VET_CREATE_OR_UPDATE_FORM);
		Vet vet = this.vetService.findVetById(vetId);
		List<Specialty> specialties = specialtyService.findSpecialties().stream().collect(Collectors.toList());
		mav.addObject("specialties1",specialties);
		mav.addObject("vet",vet);
		return mav;
	}

	@PostMapping(value = "/vets/{vetId}/edit")
	public String processUpdateVetForm(@Valid Vet vet, BindingResult result,
			@PathVariable("vetId") int vetId,
			@RequestParam(required = false) Set<Integer> specialties) {
		if (result.hasErrors()) {
			return VIEWS_VET_CREATE_OR_UPDATE_FORM;
		}
		else {
			vet.setId(vetId);
			//Actualizamos las especialidades
			Set<Specialty> specialtiesUpdated = new HashSet<Specialty>();
			if (specialties != null) {
				for (Integer s: specialties) {
					Specialty newSpecialty = this.vetService.findSpecialtyById(s);
					specialtiesUpdated.add(newSpecialty);
				}
			}
			vet.setSpecialtiesInternal(specialtiesUpdated);
			this.vetService.saveVet(vet);
			return "redirect:/vets";
		}
	}

}
