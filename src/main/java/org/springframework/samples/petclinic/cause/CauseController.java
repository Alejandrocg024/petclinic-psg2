package org.springframework.samples.petclinic.cause;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.cause.exceptions.ReachedBudgetTargetException;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/causes")
public class CauseController {
    private static final String VIEWS_DONATION_CREATE_FORM = "causes/donationCreationForm";
	private static final String VIEWS_CAUSES_CREATE_FORM = "causes/causeCreationForm";

	private CauseService causeService;
	private OwnerService ownerService;

	@Autowired
	public CauseController(CauseService causeService, AuthoritiesService authoritiesService, OwnerService ownerService) {
		this.causeService = causeService;
		this.ownerService = ownerService;
    }

    @GetMapping()
	public ModelAndView listCauses(Cause cause) {
        ModelAndView mav = new ModelAndView("causes/listCauses");
        List<Cause> causes = causeService.getCauses();
        mav.addObject("causes", causes);
        return mav;
	}

    @GetMapping("/{causeId}")
	public ModelAndView showCause(@PathVariable("causeId") int causeId, Principal principal) {
		ModelAndView mav = new ModelAndView("causes/causeDetails");
        List<Donation> donaciones = causeService.getCauseById(causeId).getDonations();
        Cause causa = causeService.getCauseById(causeId);
		mav.addObject("cause",causa);
		mav.addObject("donations", donaciones);
		return mav;
	}
	@GetMapping(value = "/new")
	public ModelAndView initCreationFormCause() {
        ModelAndView mav = new ModelAndView(VIEWS_CAUSES_CREATE_FORM);
		Cause causa = new Cause();
		mav.addObject("cause", causa);
		return mav;
    }

	@PostMapping(value = "/new")
	public ModelAndView processCauseCreationForm(@Valid Cause cause, BindingResult result) {
		ModelAndView mav = new ModelAndView(VIEWS_CAUSES_CREATE_FORM);
		if (result.hasErrors()) {
			mav.addObject("cause", cause);
			return mav;
		} else {
			causeService.saveCause(cause);
			return new ModelAndView("redirect:/causes");
		}
	}

    @GetMapping(value = "/{causeId}/donation/new")
	public ModelAndView initDonationForm(@PathVariable("causeId") int causeId, Principal principal) {
        ModelAndView mav = new ModelAndView(VIEWS_DONATION_CREATE_FORM);
		Donation donation = new Donation();
		mav.addObject("donation", donation);
		mav.addObject("cause", causeService.getCauseById(causeId));
		mav.addObject("owner", ownerService.findOwnerByUserName(principal.getName()));
		return mav;
    }

    @PostMapping(value = "/{causeId}/donation/new")
	public ModelAndView processDonationForm(@PathVariable("causeId") int causeId, Donation donation, BindingResult result, @NotNull String amount, Principal principal) {
		ModelAndView mav = new ModelAndView(VIEWS_DONATION_CREATE_FORM);
		Cause cause = causeService.getCauseById(causeId);
		donation.setOwner(ownerService.findOwnerByUserName(principal.getName()));
		donation.setAmount(Double.valueOf(amount));
		donation.setCause(cause);
		mav.addObject("cause", causeService.getCauseById(causeId));
		mav.addObject("owner", ownerService.findOwnerByUserName(principal.getName()));
		if (result.hasErrors()) {
			return mav;
		}
		else {
			try{
				if(cause.getBudgetTarget() == cause.getAmountDonated() + donation.getAmount()){
					cause.setActive(false);
					this.causeService.saveCause(cause);
				}
				this.causeService.saveDonation(donation);
			}catch(ReachedBudgetTargetException ex){
				String mensaje = donationErrorMessage(donation);
				mav.addObject("error", mensaje);
				return mav;
			}
			return new ModelAndView("redirect:/causes/"+causeId);
		}
	} 

	private String donationErrorMessage(Donation donation){
        String res = "";
		Double doub = causeService.getSumDonationsCause(donation.getCause().getId());
		if(doub ==null){
			doub = 0.0;
		}
        if(doub + donation.getAmount() > donation.getCause().getBudgetTarget()){
            res = "Con esta donación estarías superando el objetivo";
			donation.getCause().setActive(false);
        }
		return res;
}
    
}
