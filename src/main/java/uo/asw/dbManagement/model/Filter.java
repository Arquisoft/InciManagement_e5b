package uo.asw.dbManagement.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Filter {

	@Id
	@GeneratedValue
	private long id; 

	//private Set<Property> properties;
	
	public Filter() {}
	
	public long getId() {
		return id;
	}

//	public Set<Property> getProperties() {
//		return properties;
//	}
//
//	public void setProperties(Set<Property> properties) {
//		this.properties = properties;
//	}

//	public Incidence applyFilter(Incidence incidence) {
//		
//	}
	
}
