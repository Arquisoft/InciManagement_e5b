package uo.asw.dbManagement;

import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Incidence;

public interface IncidencesRepository extends CrudRepository<Incidence, Long>{

}
