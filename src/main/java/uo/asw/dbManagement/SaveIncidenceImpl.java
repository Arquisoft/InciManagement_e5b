package uo.asw.dbManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.repositories.IncidencesRepository;

@Service
public class SaveIncidenceImpl implements SaveIncidence{
	
	@Autowired
	private IncidencesRepository incidencesRepository;

	@Override
	public void saveIncidence(Incidence incidence) {
		incidencesRepository.save(incidence);
	}
	
}
