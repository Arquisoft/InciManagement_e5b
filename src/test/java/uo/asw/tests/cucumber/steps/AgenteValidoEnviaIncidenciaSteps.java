package uo.asw.tests.cucumber.steps;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uo.asw.InciManagerE5bApplication;
import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.inciManager.IdentifierGenerator;
import uo.asw.inciManager.IncidenceService;

@ContextConfiguration(classes=InciManagerE5bApplication.class, loader=SpringApplicationContextLoader.class)
@SpringBootTest
public class AgenteValidoEnviaIncidenciaSteps {
	
	@Autowired
	private IncidenceService incidenceService;

	private Agent agent;
	private Incidence incidence;
	private boolean ok;
	
	@Given("^el agente con el login \"([^\"]*)\" , el password \"([^\"]*)\" y el kind \"([^\"]*)\"$")
	public void el_agente_con_el_login_el_password_y_el_kind(String login, String password, String kind) throws Throwable {
	   agent = new Agent(login, password, kind);
	}

	@Given("^los siguientes datos de la incidencia: name \"([^\"]*)\" , description \"([^\"]*)\"$")
	public void los_siguientes_datos_de_la_incidencia_name_description(String name, String description) throws Throwable {	    
	    // Creamos una incidencia básica con los datos pasados y un identifier generado automaticamente
	    incidence = new Incidence(IdentifierGenerator.getIdentifier());
	    incidence.setName(name);
	    incidence.setDescription(description);
	}

	@When("^el agente envía la incidencia$")
	public void el_agente_envía_la_incidencia() throws Throwable {
	   // Enviamos la incidencia y comprobamos que el metodo retorna true
		ok = incidenceService.manageIncidence(agent.getIdentifier(), agent.getPassword(), agent.getKind(), incidence);
	}

	@Then("^la incidencia es enviada correctamente$")
	public void la_incidencia_es_enviada_correctamente() throws Throwable {
		assertTrue(ok);
	}

}
