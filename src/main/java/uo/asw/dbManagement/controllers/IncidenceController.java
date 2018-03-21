package uo.asw.dbManagement.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.services.IncidenceService;

@Controller
public class IncidenceController {

	@Autowired
	private IncidenceService incidenceService;

	@RequestMapping(value = "/incidence/add")
	public String addIncidence(Model model) {

		return "incidence/add";
	}

	@RequestMapping(value = "/incidence/add", method = RequestMethod.POST)
	public String addIncidence(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "kind") String kind,
			@RequestParam(value = "etiquetas") String etiquetas,
			@RequestParam(value = "propiedades") String propiedades, @ModelAttribute Incidence incidence) {// @ModelAttribute
																										// Mark mark){
		String uuid = UUID.randomUUID().toString().replace("-", "");
		incidence.setIdentifier(uuid);
		incidenceService.listIncidence(incidence,etiquetas,propiedades);
		if (incidenceService.manageIncidence(username, password, kind, incidence))
			return "redirect:/index";
		else
			return "redirect:/incidence/error"; // TODO: completar
	}
}
