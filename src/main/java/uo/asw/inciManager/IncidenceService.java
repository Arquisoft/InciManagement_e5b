package uo.asw.inciManager;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import uo.asw.apacheKafka.KafkaProducer;
import uo.asw.apacheKafka.SendIncidence;
import uo.asw.dbManagement.SaveIncidence;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Property;
import uo.asw.inciReporter.ReportIncidence;

@Service
public class IncidenceService {

	@Autowired
	private SaveIncidence saveIncidence;

	@Autowired
	private SendIncidence sendIncidence;
	
	@Autowired
	private ReportIncidence reportIncidence;

	private static final Logger logger = Logger.getLogger(KafkaProducer.class);

	private String generarJSON(Incidence incidence) {
//		JSONObject json = new JSONObject();
//		json.put("identifier", incidence.getIdentifier());
//		json.put("login", incidence.getLogin());
//		json.put("password", incidence.getPassword());
//		json.put("kind", incidence.getKind());
//		json.put("name", incidence.getName());
//		json.put("description", incidence.getDescription());
//		json.put("location", incidence.getLocation());
//		json.put("tags", incidence.getTags());
//		json.put("properties", incidence.getProperties());
//		json.put("status", incidence.getStatus());
//		json.put("operatorIdentifier", incidence.getOperatorIdentifier());
//		json.put("expiration", incidence.getExpiration());
//
//		return json.toString();
		return "";
	}

	public boolean manageIncidence(String name, String password, String kind, Incidence incidence) {
		if (loginCorrecto(name, password, kind)) {
			saveIncidence.saveIncidence(incidence);
			sendIncidence.sendIncidence(generarJSON(incidence));
			return true;
		} else {
			reportIncidence.reportIncidence(incidence);
			return false;
		}
	}

	// TODO - revisar si realiza bien la peticion
	public boolean loginCorrecto(String login, String password, String kind) {
		logger.info("Sending POST request to url http://localhost:8080/user ");
		String url = "http://localhost:8080/user"; // Supuesta url desde donde
													// se envían las peticiones
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		JSONObject peticion = new JSONObject();
		peticion.put("login", login);
		peticion.put("password", password);
		peticion.put("kind", kind);
		
		HttpEntity<String> entity = new HttpEntity<String>(peticion.toString(), header);
		ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
		HttpStatus responseCode = response.getStatusCode();
		
		return responseCode.equals(HttpStatus.OK);
	}

	public void listIncidence(Incidence incidence, String etiquetas, String propiedades) {
		incidence.setTags(procesarString(incidence, etiquetas));
		incidence.setProperties(procesarPropiedades(procesarString(incidence, propiedades)));
	}

	private Set<String> procesarString(Incidence incidence, String x) {
		Set<String> list = new HashSet<String>();
		for (String string : x.split(";")) {
			list.add(string);
		}
		return list;
	}

	private Set<Property> procesarPropiedades(Set<String> list) {
		Set<Property> list2 = new HashSet<Property>();
		for (String s : list) {
			String[] temp = s.split(":");
			if (temp.length == 2) {
				Property pro = new Property(temp[0], temp[1]);
				list2.add(pro);
			}
		}
		return list2;
	}
}
