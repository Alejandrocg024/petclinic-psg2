package org.springframework.samples.petclinic.cause;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CauseRepository extends CrudRepository<Cause, Integer> {

	@Query("SELECT c FROM Cause c WHERE c.id=?1")
	Cause findCauseById(Integer id);
	
	List<Cause> findAll();

	@Query("SUM(d.amount) FROM Donation d WHERE d.cause.id ?=1 ")
	Double sumDonationsCause(Integer id);
	
	
}
