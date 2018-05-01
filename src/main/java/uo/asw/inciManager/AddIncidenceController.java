package uo.asw.inciManager;

import java.util.HashSet;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.model.Property;

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
	
	//TODO . revisar status por si no se marca siemore a true!
	@RequestMapping(value = "/incidence/add", method = RequestMethod.POST)
	public String addIncidence(@RequestParam String login,@RequestParam String password,@RequestParam String kind, 
			@RequestParam String name, @RequestParam String description, @RequestParam String location, 
			@RequestParam String tags, @RequestParam String properties) {
		
				
		//login=53143423423P
		Agent a = new Agent();// id=1, identifier=53143423423P
		Operator o = new Operator();
		
		Incidence incidence = new Incidence(login, a, o, "asda", "asdad", "asdad", new HashSet<String>(), new HashSet<Property>(), "open", "12_12_1996");
		String uuid = UUID.randomUUID().toString().replace("-", "");
		incidence.setIdentifier(uuid);
		System.err.println(incidence);
		
//		Incidence incidence = new Incidence(uuid);

//		incidenceService.listIncidence(incidence,etiquetas,propiedades);
//		if (incidenceService.manageIncidence(username, password, kind, incidence))
//			return "redirect:/index";
//		else
//			return "redirect:/error"; // TODO: completar
		
		return "index";
	}
//
//	@Override
//	public String addIncidence(String username, String password, String kind, String etiquetas, String propiedades,
//			Incidence incidence) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
