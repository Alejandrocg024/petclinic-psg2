package org.springframework.samples.petclinic.hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pet-hotel")
public class PetHotelController {
    @Autowired
    private PetRepository petRepository;
    
    @Autowired
    private PetHotelBookingRepository bookingRepository;

    @Autowired
    private OwnerRepository ownerRepository;
    
    @GetMapping("/book")
    public ModelAndView showBookingForm() {
        ModelAndView mav = new ModelAndView("bookingForm");
        List<Pet> pets = petRepository.findByOwnerId(1);
        mav.addObject("pets", pets);
        mav.addObject("owner", ownerRepository.findById(1));
        return mav;
    }
    
    @PostMapping("/book")
    public String submitBookingForm(@ModelAttribute("booking") PetHotelBooking booking) {
        bookingRepository.save(booking);
        return "redirect:/pet-hotel/bookings";
    }
    
    @GetMapping("/bookings")
    public String showBookingList(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "pet-hotel/booking-list";
    }
}
