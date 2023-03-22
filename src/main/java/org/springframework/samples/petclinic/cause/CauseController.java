package org.springframework.samples.petclinic.cause;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CauseController {
    private static final String VIEWS_DONATION_CREATE_OR_UPDATE_FORM = "cause/createForm";

    @Autowired
	private CauseService causeService;

	@Autowired
	public CauseController(CauseService causeService, AuthoritiesService authoritiesService) {
		this.causeService = causeService;
    }

    @GetMapping(value = "/causes")
	public ModelAndView listCauses(Cause cause, BindingResult result) {
        ModelAndView mav = new ModelAndView("causes/findCauses");
        List<Cause> causas = causeService.getCauses();
        Map<Cause, Double> map = new HashMap<Cause, Double>();
        for(Cause causa: causas){
            Double donacionesTotal = causeService.getSumDonationsCause(causa.getId());
            map.put(causa, donacionesTotal);
        }
        mav.addObject("Causas", map.keySet());
        mav.addObject("DonacionTotal", map.values());
        return mav;
	}

    @GetMapping("/causes/{causeId}")
	public ModelAndView showCause(@PathVariable("causeId") int causeId, Principal principal) {
		ModelAndView mav = new ModelAndView("causes/causeDetails");
        List<Donation> donaciones = causeService.getDonationsCauseById(causeId);
        Cause causa = causeService.getCauseById(causeId);
		mav.addObject("causa",causa);
		mav.addObject("donaciones", donaciones);
		return mav;
	}
    @GetMapping(value = "/causes/{causeId}/donation/new")
	public ModelAndView initCreationForm(@PathVariable("causeId") int causeId) {
        ModelAndView mav = new ModelAndView(VIEWS_DONATION_CREATE_OR_UPDATE_FORM);
		Cause causa = new Cause();
		mav.addObject("causa", causa);
		return mav;
    }

    @PostMapping(value = "/causes/{causeId}/donation/new")
	public String processCreationForm(@Valid Donation donacion, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
		}
		else {
			causeService.saveDonation(donacion);
			return "redirect:/causes/" + donacion.getId();
		}
	}
    
}
