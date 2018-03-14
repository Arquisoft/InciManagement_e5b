package uo.asw.dbManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Incidence;

public interface IncidencesRepository extends CrudRepository<Incidence, Long>{
		
	@Query("SELECT o.incidences FROM Operator o WHERE o.id = ?1") //TODO - revisar
	public List<Incidence> getOperatorIncidences(Long idOperator);
	
	//@Query("SELECT i FROM Incidence i WHERE i.tags MEMBER OF ?1") //TODO - revisar
	@Query("SELECT i FROM Incidence i where ?1 IN i.tags")//TODO - está mal!! Hay que implementarlo
	public List<Incidence> getIncidencesOfCategory(String category);
}
