package uo.asw.inciManager;

import uo.asw.dbManagement.model.Incidence;

public interface AddIncidence {

	String addIncidence();
	
//	String addIncidence(String username, String password, String kind, String etiquetas,
//			String propiedades, Incidence incidence);
	
	String addIncidence(Incidence incidence);
}
