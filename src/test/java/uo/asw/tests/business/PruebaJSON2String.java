package uo.asw.tests.business;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.InciManagerE5bApplication;
import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.model.Property;
import uo.asw.inciManager.IncidenceService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciManagerE5bApplication.class)
@WebAppConfiguration
/**
 * Prueba el componente Filter
 */

public class PruebaJSON2String {
	
	@Autowired
	private IncidenceService incidenceService;

 	String json = "{"
			+ "\"identifier\":\"AOU123\","
			+ "\"password\":\"lnoi\","
			+ "\"kind\":\"Person\","
			+ "\"name\":\"Incendio\","
			+ "\"operatorIdentifier\":\"12345\","
			+ "\"description\":\"Incendio pequeño\","
			+ "\"location\":\"+10.1,-15.66\","
			+ "\"expiration\":\"2019-12-30 12:32:11\","
			+ "\"login\":\"Manuel\","
			+ "\"properties\":["
			+ "{\"name\":\"temperatura\",\"value\":\"100\"},"
			+ "{\"name\":\"humedad\",\"value\":\"0\"}"
			+ "],"
			+ "\"tags\":[\"tag1\",\"tag2\"],"
			+ "\"status\":\"open\""
		+ "}";
	
	@Test
	public void test() {
		
		Agent a = new Agent("Manuel","lnoi","Person");
		Operator o = new Operator("12345", "Pepe");
		
		Incidence i = new Incidence();
		i.setIdentifier("AOU123");
		i.setAgent(a);
		i.setName("Incendio");
		i.setDescription("Incendio pequeño");
		i.setLocation("+10.1,-15.66");
		Set<String> tags = new HashSet<String>();
		tags.add("tag1");
		tags.add("tag2");
		i.setTags(tags);
		Set<Property> properties = new HashSet<Property>();
		properties.add(new Property("temperatura", "100"));
		properties.add(new Property("humedad", "0"));
		i.setProperties(properties);
		i.setStatus("open");
		i.setOperator(o);
		i.setExpiration("2019-12-30 12:32:11");
		
		assertEquals(incidenceService.generarJSON(i), json);
	}
	
}
