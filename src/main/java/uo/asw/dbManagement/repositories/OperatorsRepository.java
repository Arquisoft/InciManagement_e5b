package uo.asw.dbManagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Operator;

public interface OperatorsRepository extends CrudRepository<Operator, Long>{

	@Query("SELECT o FROM Operator o WHERE o.identifier = ?1")
	Operator getOperator(String identifier);
	
}
