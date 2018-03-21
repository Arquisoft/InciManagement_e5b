package uo.asw.inciManager;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import uo.asw.apacheKafka.producer.KafkaProducer;
import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.reporter.InciReporter;

public class InciManager {

	
	private Incidence inci;
	private static final Logger logger = Logger.getLogger(KafkaProducer.class);

	public InciManager(JSONObject jsonInci) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		inci = new Incidence(uuid);
		
		// Crear incidencia a partir de los datos del json
	}

	/**
	 * Comprueba si la incidencia realizada es correcta. Si lo es la guarda en
	 * la base de datos y la envia a apache kafka. En caso contrario la reporta.
	 */
	public void manageIncidence() {
		if(checkAgent()){
			persistIncidence();
			sendIncidence();
		}else{ 
			reportIncidence();
		}
	}

	/**
	 * Comprueba si el agente que realiza la incidencia esta o no en el sistema.
	 * Devuelve true si lo esta y false en caso contrario.
	 */
	private boolean checkAgent() {
		logger.info("Sending POST request to url http://localhost:8080/user ");
		String url = "http://localhost:8080/user";//Supuesta url desde donde se envían las peticiones
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		JSONObject peticion = new JSONObject();
		peticion.put("login", "");		//	Cambiar valor del login -> inci.getLogin()
        peticion.put("password", "");	//	Cambiar valor del password -> inci.getPassword()
		peticion.put("kind", "");		//	Cambiar valor del kind -> inci.getKind()
        HttpEntity<String> entity = new HttpEntity<String>(peticion.toString(), header);
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
        HttpStatus responseCode = response.getStatusCode();
        return responseCode.equals(HttpStatus.OK);
	}


	/**
	 * Llama a IncidenceReporter cuando una incidencia es incorrecta
	 */
	private void reportIncidence() {
		InciReporter.reportInci(inci);
	}

	/**
	 * Almacena la incidencia en la bd
	 */
	private void persistIncidence() {
		
	}

	/**
	 * Envia la incidencia a apache kafka
	 */
	private void sendIncidence() {
		String msg = "";						// Mensaje a enviar a traves de Apache Kafka
		KafkaProducer kp = new KafkaProducer();
		kp.send("incidences", msg);				// "incidences" es el topic para las incidencias
	}

}
