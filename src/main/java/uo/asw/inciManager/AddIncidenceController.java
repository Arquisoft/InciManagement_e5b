package uo.asw.inciManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uo.asw.dbManagement.model.Incidence;

@Controller
public class AddIncidenceController{//  implements AddIncidence{ TODO implementar la interfaz !!!!

	@Autowired
	private IncidenceService incidenceService;
	
	
	//TODO quitar
	@ResponseBody()
	@RequestMapping("/prueba")
	public String prueba() {
		
		return "" + incidenceService.loginCorrecto("31668313G", "1234", "Person");
	}
	
	@RequestMapping(value = "/incidence/add")
	public String addIncidence() {
		return "incidence/add";
	}
	
	@RequestMapping(value = "/incidence/add", method = RequestMethod.POST)
	public String addIncidence(@RequestParam String login,@RequestParam String password,@RequestParam String kind, 
			@RequestParam String name, @RequestParam String description, @RequestParam String location, 
			@RequestParam String tags, @RequestParam String properties) {
		
		// Se crea la incidencia con los campos del formulario web.
		Incidence incidence = incidenceService.createIncidence(name, description, location, tags, properties);
		
		// Si existe el agente, se devuelve a la vista inicial. Si no, a la vista de error.
		if (incidenceService.manageIncidence(login, password, kind, incidence))
			return "index";
		else
			return "error";
		
	}
	
//
//	@Override
//	public String addIncidence(String username, String password, String kind, String etiquetas, String propiedades,
//			Incidence incidence) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
