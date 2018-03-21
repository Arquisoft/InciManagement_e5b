package uo.asw.dbManagement;

import java.util.List;

import uo.asw.dbManagement.model.Incidence;

public interface DBManagementFacade {
	
	public Incidence getIncidence(Long idIncidence);
	
	public List<Incidence> getOperatorIncidences(Long idOperator); //TODO - Cuando este implementado y funcione, hacer que devuelva Page<Incidence> para meter paginacion

	public List<Incidence> getIncidencesOfCategory(String[] categories); //TODO - Cuando este implementado y funcione, hacer que devuelva Page<Incidence> para meter paginacion
	
	public void updateIncidence(Incidence incidence);
	
}
