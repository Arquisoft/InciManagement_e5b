package uo.asw.dbManagement.services;

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

import uo.asw.apacheKafka.producer.KafkaProducer;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Property;
import uo.asw.dbManagement.repositories.IncidencesRepository;
import uo.asw.reporter.InciReporter;

@Service
public class IncidenceService {

	@Autowired
	private IncidencesRepository incidenceRepository;

	@Autowired
	private KafkaProducer kafkaProducer;

	private static final Logger logger = Logger.getLogger(KafkaProducer.class);

	public void sendIncidence(Incidence incidence) {
		kafkaProducer.send("incidences", generarJSON(incidence));
	}

	private String generarJSON(Incidence incidence) {
		JSONObject json = new JSONObject();
		json.put("identifier", incidence.getIdentifier());
		json.put("login", incidence.getLogin());
		json.put("password", incidence.getPassword());
		json.put("kind", incidence.getKind());
		json.put("name", incidence.getName());
		json.put("description", incidence.getDescription());
		json.put("location", incidence.getLocation());
		json.put("tags", incidence.getTags());
		json.put("properties", incidence.getProperties());
		json.put("status", incidence.getStatus());
		json.put("operatorIdentifier", incidence.getOperatorIdentifier());
		json.put("expiration", incidence.getExpiration());

		return json.toString();
	}
	

	

	public boolean manageIncidence(String name, String password, String kind, Incidence incidence) {
		if (loginCorrecto(name, password, kind)) {
			persistIncidence(incidence);
			sendIncidence(incidence);
			return true;
		} else {
			reportIncidence(incidence);
			return false;
		}
	}

	private void persistIncidence(Incidence incidence) {
		incidenceRepository.save(incidence);
	}

	private boolean loginCorrecto(String name, String password, String kind) {
		logger.info("Sending POST request to url http://localhost:8080/user ");
		String url = "http://localhost:8080/user"; // Supuesta url desde donde
													// se envían las peticiones
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		JSONObject peticion = new JSONObject();
		peticion.put("login", name);
		peticion.put("password", password);
		peticion.put("kind", kind);
		HttpEntity<String> entity = new HttpEntity<String>(peticion.toString(), header);
		ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
		HttpStatus responseCode = response.getStatusCode();
		return responseCode.equals(HttpStatus.OK);
	}

	private void reportIncidence(Incidence incidence) {
		InciReporter.reportInci(incidence);
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
	
	/*
	  private Incidence JSON_To_Inci(String data) {
		String identifier="";
		String login;
		String password;
		String kind;
		String name;
		String description="";
		String location="";
		Set<String> tags = null;
		Map<String,Object> additional = null;
		Set<Property> properties = null;
		
		JSONObject obj= new JSONObject(data);
		
		identifier= obj.optString("identifier");
		login=obj.getString("login");
		password=obj.getString("password");
		kind=obj.getString("kind");
		name=obj.getString("name");
		description=obj.getString("description");
		location=obj.getString("location");
		
		JSONArray jsonTags = obj.getJSONArray("tags");
		for (int i=0; i<jsonTags.length(); i++) {
		    tags.add(jsonTags.getString(i));
		}
		
		JSONArray jsonAdditional = obj.getJSONArray("additional");
		for (int i=0; i<jsonAdditional.length(); i++) {
		    JSONObject item = jsonAdditional.getJSONObject(i);
		    String key=item.getString("nombre");
		    Object value= item.get("valor");
		    additional.put(key, value);
		}
		
		JSONArray jsonProperties = obj.getJSONArray("properties");
		for (int i=0; i<jsonProperties.length(); i) {
		    JSONObject itemProp = jsonProperties.getJSONObject(i);
		    String key=itemProp.getString("propiedad");
		    String value=itemProp.getString("valor");
		    Property p= new Property(key,value);
		    properties.add(p);
		}
		
		Agent a= new Agent();
		a.setIdentifier(login);
		a.setPassword(password);
		a.setKind(kind);
		
		Incidence inc= new Incidence();
		inc.setIdentifier(identifier);
		inc.setName(name);
		inc.setLogin(login);
		inc.setPassword(password);
		inc.setKind(kind);
		inc.setLocation(location);
		inc.setDescription(description);
		inc.setProperties(properties);
		inc.setTags(tags);
		//inc.setAdditional(additional);
		
		return inc;
		
	}
	 */

}
