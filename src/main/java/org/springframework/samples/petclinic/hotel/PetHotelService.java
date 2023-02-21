package org.springframework.samples.petclinic.hotel;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

@Service
public class PetHotelService {
    private PetHotelBookingRepository petHotelRepository;

    public void save(PetHotelBooking p){
        this.petHotelRepository.save(p); 
    }
    @Transactional(readOnly = true)
    public List<PetHotelBooking> findByOwner(Integer id){
        return this.petHotelRepository.findByOwnerId(id);
    }
    
}
