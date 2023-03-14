package org.springframework.samples.petclinic.hotel;

import java.security.Principal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.hotel.exceptions.DuplicatedPetInHotelException;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private static final String VIEWS_HOTEL_PET_BOOKING_CREATE = "booking/createUpdateBookingForm";
    private static final String HOTEL_PET_BOOKING_LIST = "booking/bookingList";

    @Autowired
    private PetService petService;
    
    @Autowired
    private BookingService bookingService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{ownerId}/new")
	public String showBookingForm(@PathVariable("ownerId") Integer ownerId, ModelMap model) {
		Booking booking = new Booking();
        Owner owner = ownerService.findOwnerById(ownerId);
        booking.setOwner(owner);
		model.put("booking", booking);
        model.put("owner", owner);
        model.put("pets", petService.findPetsByOwner(ownerId));
		return VIEWS_HOTEL_PET_BOOKING_CREATE;
	}

    @PostMapping("/{ownerId}/new")
    public ModelAndView processCreationForm(@PathVariable("ownerId") Integer ownerId, @Valid Booking booking, BindingResult result) {
		ModelAndView res = new ModelAndView(VIEWS_HOTEL_PET_BOOKING_CREATE);
        Owner owner = ownerService.findOwnerById(ownerId);
        res.addObject("owner", owner);
        res.addObject("pets", petService.findPetsByOwner(ownerId));
        if (result.hasErrors()) {
			return res;
        } else {
            try{
                booking.setOwner(owner);
			    this.bookingService.save(booking);
            }catch(DuplicatedPetInHotelException ex){
                String message = dateErrorMessage(booking);
                res.addObject("error", message);
                return res;
            }
            return new ModelAndView("redirect:/booking/{ownerId}");
		}
	}

    private String dateErrorMessage(Booking booking){
        String res = "";
        if(booking.getStartDate().isAfter(booking.getEndDate()) || booking.getStartDate().isEqual(booking.getEndDate())){
            res = "La fecha de fin debe ser posterior a la fecha inicial";
        } else if(booking.getStartDate().isBefore(LocalDate.now())){
            res = "La fecha de inicio no debe ser posterior a la fecha actual";
        } else{
            res = "La mascota ya tiene una reserva en esa fecha";
        }
        return res;
    }
    
    
    @GetMapping("/{ownerId}")
    public ModelAndView showBookingList(@PathVariable("ownerId") Integer ownerId, Principal principal) {
        ModelAndView mav = new ModelAndView(HOTEL_PET_BOOKING_LIST);
        mav.addObject("bookings", bookingService.findBookingsByOwner(ownerId));
        mav.addObject("owner", ownerService.findOwnerById(ownerId));
        User user = userService.findUser(principal.getName()).get();
        Boolean EsUserLogeado;
        if(user.getUsername().equals("admin1")){
            EsUserLogeado = true;
        } else {
            Integer owner = ownerService.findOwnerByUserName(user.getUsername()).getId();
            EsUserLogeado = owner.equals(ownerId);
        }
		mav.addObject("esUserLogeado", EsUserLogeado);
        return mav;
    }
}
