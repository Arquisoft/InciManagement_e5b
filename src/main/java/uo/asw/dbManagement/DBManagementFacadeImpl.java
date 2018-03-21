package uo.asw.dbManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.repositories.IncidencesRepository;

@Service
public class DBManagementFacadeImpl implements DBManagementFacade{
	
	@Autowired
	private IncidencesRepository incidencesRepository;
	
	public Incidence getIncidence(Long idIncidence) {
		return incidencesRepository.findOne(idIncidence);
	}
	
	public List<Incidence> getOperatorIncidences(Long idOperator) {
		return incidencesRepository.getOperatorIncidences(idOperator);
	}

	public List<Incidence> getIncidencesOfCategory(String[] categories) {
		
		List<Incidence> incidenciasConAlMenosUnaDeEsasCategorias=new ArrayList<Incidence>();
		for (String category : categories) {
			List<Incidence> incidenciasConEsaCategoria=incidencesRepository.getIncidencesOfCategory(category);
			for (Incidence incidence : incidenciasConEsaCategoria) {
				if(!incidenciasConAlMenosUnaDeEsasCategorias.contains(incidence)) {
					incidenciasConAlMenosUnaDeEsasCategorias.add(incidence);
				}
			}
		}
		return incidenciasConAlMenosUnaDeEsasCategorias;
		
	}

	public void updateIncidence(Incidence incidence) {
		incidencesRepository.save(incidence);
	}
}
