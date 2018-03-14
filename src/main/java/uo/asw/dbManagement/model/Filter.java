package uo.asw.dbManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Filter {

	@Id
	@GeneratedValue
	private long id; 
	
}
