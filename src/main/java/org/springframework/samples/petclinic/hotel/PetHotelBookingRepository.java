package org.springframework.samples.petclinic.hotel;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PetHotelBookingRepository extends CrudRepository<PetHotelBooking, Integer> {

    @Query("SELECT p FROM PetHotelBooking p WHERE p.owner.id=?1")
	List<PetHotelBooking> findByOwnerId(Integer id);
    
    
}

