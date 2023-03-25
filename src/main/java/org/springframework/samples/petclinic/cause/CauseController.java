package org.springframework.samples.petclinic.cause;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private static final String VIEWS_DONATION_CREATE_OR_UPDATE_FORM = "causes/createForm";

    @Autowired
	private CauseService causeService;
	private OwnerService ownerService;

	@Autowired
	public CauseController(CauseService causeService, AuthoritiesService authoritiesService, OwnerService ownerService) {
		this.causeService = causeService;
		this.ownerService = ownerService;
    }

    @GetMapping(value = "")
	public ModelAndView listCauses(Cause cause, BindingResult result) {
        ModelAndView mav = new ModelAndView("causes/findCauses");
        List<Cause> causas = causeService.getCauses();
		List<CauseDonation> causeD = new ArrayList<CauseDonation>();
        for(Cause causa: causas){
            Double donacionesTotal = causeService.getSumDonationsCause(causa.getId());
            CauseDonation causaDonation = new CauseDonation(causa, donacionesTotal);
			causeD.add(causaDonation);
		}
        mav.addObject("causaDonation", causeD);
        return mav;
	}

    @GetMapping("/{causeId}")
	public ModelAndView showCause(@PathVariable("causeId") int causeId, Principal principal) {
		ModelAndView mav = new ModelAndView("causes/causeDetails");
        List<Donation> donaciones = causeService.getDonationsCauseById(causeId);
        Cause causa = causeService.getCauseById(causeId);
		mav.addObject("cause",causa);
		mav.addObject("donaciones", donaciones);
		return mav;
	}
    @GetMapping(value = "/{causeId}/donation/new")
	public ModelAndView initCreationForm(@PathVariable("causeId") int causeId) {
        ModelAndView mav = new ModelAndView(VIEWS_DONATION_CREATE_OR_UPDATE_FORM);
		Donation donacion = new Donation();
		mav.addObject("donation", donacion);
		return mav;
    }

    @PostMapping(value = "/{causeId}/donation/new")
	public ModelAndView processCreationForm(@PathVariable("causeId") int causeId, @Valid Donation donation, BindingResult result, @NotNull String amount, Principal principal) {
		ModelAndView mav = new ModelAndView(VIEWS_DONATION_CREATE_OR_UPDATE_FORM);
		donation.setOwner(ownerService.findOwnerByUserName(principal.getName()));
		donation.setAmount(Double.valueOf(amount));
		donation.setCause(causeService.getCauseById(causeId));
		mav.addObject("amount", donation.getAmount());
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return mav;
		}
		else {
			try{
				donation.setDonationDate(LocalDate.now());
				this.causeService.saveDonation(donation);
			}catch(ReachedBudgetTargetException ex){
				String mensaje = donationErrorMessage(donation);
				mav.addObject("error", mensaje);
				return mav;
			}
			return new ModelAndView("redirect:/causes/{causeId}");
		}
	} 


	private String donationErrorMessage(Donation donation){
        String res = "";
        if(causeService.getSumDonationsCause(donation.getCause().getId()) + donation.getAmount() > donation.getCause().getBudgetTarget()){
            res = "Con esta donación estarías superando el objetivo";
			donation.getCause().setActive(false);
        } else if(donation.getCause().getActive()==false){
            res = "La causa ya a llegado a su objetivo y no se puede donar";
    	}
		return res;
}
    
}
