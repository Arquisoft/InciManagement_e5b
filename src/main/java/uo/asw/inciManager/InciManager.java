package uo.asw.inciManager;

import java.util.UUID;

import org.json.JSONObject;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.reporter.InciReporter;

public class InciManager {

	private Incidence inci;

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
		if(checkAgent() && checkIncidence()){
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
		return false;
	}

	private boolean checkIncidence() {
		return false;
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

	}

}
