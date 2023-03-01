package org.springframework.samples.petclinic.hotel;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findAll();

    @Query("SELECT p FROM Booking p WHERE p.owner.id=?1")
	List<Booking> findBookingsByOwnerId(Integer id);
    
    
}

