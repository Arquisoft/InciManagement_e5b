package uo.asw.inciManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import uo.asw.apacheKafka.KafkaProducer;
import uo.asw.apacheKafka.SendIncidence;
import uo.asw.dbManagement.GetAgent;
import uo.asw.dbManagement.GetOperator;
import uo.asw.dbManagement.SaveIncidence;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.model.Property;
import uo.asw.inciReporter.ReportIncidence;

@Service
public class IncidenceService {

	@Autowired
	private SaveIncidence saveIncidence;

	@Autowired
	private GetAgent getAgent;

	@Autowired
	private GetOperator getOperator;

	@Autowired
	private SendIncidence sendIncidence;

	@Autowired
	private ReportIncidence reportIncidence;

	private static final Logger logger = Logger.getLogger(KafkaProducer.class);

	public String generarJSON(Incidence incidence) {
		JSONObject json = new JSONObject();

		json.put("identifier", incidence.getIdentifier());
		json.put("login", incidence.getAgent().getIdentifier());
		json.put("password", incidence.getAgent().getPassword());
		json.put("kind", incidence.getAgent().getKind());
		json.put("name", incidence.getName());
		json.put("description", incidence.getDescription());
		json.put("location", incidence.getLocation());
		json.put("tags", incidence.getTags());
		json.put("properties", incidence.getProperties());
		json.put("status", incidence.getStatus());
		json.put("operatorIdentifier", incidence.getOperator().getIdentifier());
		json.put("expiration", incidence.getExpiration());

		return json.toString();
	}

	public Incidence createIncidence(String name, String description, String location, String tagsWeb,
			String propertiesWeb) {

		String identifier = IdentifierGenerator.getIdentifier();
		Set<String> tags = procesarString(tagsWeb);
		Set<Property> properties = procesarPropiedades(propertiesWeb);

		// Las incidencias caducan en 3 meses desde el momento en que se crean
		Calendar fechaCaducidad = Calendar.getInstance();
		fechaCaducidad.add(Calendar.MONTH, 3);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String caducidad = formatter.format(fechaCaducidad);

		return new Incidence(identifier, name, description, location, tags, properties, "open", caducidad);
	}

	public boolean manageIncidence(String login, String password, String kind, Incidence incidence) {

		if (loginCorrecto(login, password, kind)) {
			// Si el agente existe, se obtiene una referenia a el de la BD y se asocia con
			// la incidencia
			incidence.setAgent(getAgent.getAgent(login));

			// Se recupera un operario de la BD y se asocia con la incidencia
			incidence.setOperator(getBestOperator());

			// Se guarda la incidencia en la BD
			saveIncidence.saveIncidence(incidence);

			// Se envia la incidencia a Apache Kafka
			sendIncidence.sendIncidence(generarJSON(incidence));

			return true;
		} else {
			// Si el agente no existe, se reporta el error
			reportIncidence.reportIncidence(incidence);
			return false;
		}
	}

	// Variable para seleccionar el operador al que se le asigna la incidencia
	private static int operatorSelector = 0;
	// Identificadores de los operarios a los que se les asignan las incidencias
	private String operatorIdentifier1 = "99999999A";
	private String operatorIdentifier2 = "AAAAAAA2";

	private Operator getBestOperator() {
		String operatorIdentifier;

		if (operatorSelector % 2 == 0) {
			operatorIdentifier = operatorIdentifier1;
		} else {
			operatorIdentifier = operatorIdentifier2;
		}

		operatorSelector++;

		return getOperator.getOperator(operatorIdentifier);
	}

	public boolean loginCorrecto(String login, String password, String kind) {
		logger.info("Sending POST request to url http://localhost:8080/user with this data: [login: " + login
				+ ", password: " + password + ", kind: " + kind + "]");
		String url = "http://localhost:8080/user"; // Supuesta url desde donde
		// se envían las peticiones
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);

		JSONObject peticion = new JSONObject();
		peticion.put("login", login);
		peticion.put("password", password);
		peticion.put("kind", kind);

		try {
			HttpEntity<String> entity = new HttpEntity<String>(peticion.toString(), header);
			ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
			HttpStatus responseCode = response.getStatusCode();
			return responseCode.equals(HttpStatus.OK);

		} catch (HttpClientErrorException e) {
			return false;
		}

	}

	private Set<String> procesarString(String field) {
		Set<String> list = new HashSet<String>();
		for (String string : field.split(";")) {
			list.add(string);
		}
		return list;
	}

	private Set<Property> procesarPropiedades(String tagsWeb) {
		Set<String> list = procesarString(tagsWeb);

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
