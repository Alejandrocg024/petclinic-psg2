package org.springframework.samples.petclinic.cause;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Integer> {

    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.cause.id =?1 ")
	Double sumDonationsCause(Integer id);

    List<Donation> findAll();
    
}
