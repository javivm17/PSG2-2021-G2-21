package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cause;

public interface CauseRepository extends CrudRepository<Cause, Integer> {

	@Query("SELECT SUM(d.amount) FROM Donation d WHERE d.cause.id = :causeId")
	Integer getCurrentBudget(@Param("causeId") int causeId) throws DataAccessException;
}
