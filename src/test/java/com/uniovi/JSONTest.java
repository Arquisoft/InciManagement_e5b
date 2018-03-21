package com.uniovi;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.services.IncidenceService;

public class JSONTest {
	
	@Test
	public void test() {
		JSONParser parser = new JSONParser();
		JSONObject a = null;
		try {
			a = (JSONObject) parser.parse(new FileReader("src\\main\\resources\\static\\test.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IncidenceService inser= new IncidenceService();
		Incidence i= inser.JSON_To_Inci(a.toString());
		
		
		assertEquals(i.getIdentifier(),"identificadorPrueba");
		assertEquals(i.getAgent().getIdentifier(),"usuarioPrueba");
		assertEquals(i.getAgent().getPassword(),"passwordPrueba");
		assertEquals(i.getAgent().getKind(),"tipoPrueba");
		assertEquals(i.getName(),"nombrePrueba");
		assertEquals(i.getDescription(),"descripcionPrueba");
		assertEquals(i.getLocation(),"coordenadasPrueba");
		assertEquals(i.getTags().toString(),"[tag1, tag2]");
		assertEquals(i.getProperties().toString(),"[Property [property=propiedadPrueba, value=valorPrueba]]");
		//assertEquals(i.getAdditional().toString(),"additional");
	}

}
