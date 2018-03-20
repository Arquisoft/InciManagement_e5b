package uo.asw.dbManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.services.IncidenceService;

@Controller
public class IncidenceController {
	
	
	@Autowired
	private IncidenceService incidenceService;
	
	@RequestMapping(value="/incidence/add")
	public String getMark(Model model){
		
		return "incidence/add";
	}
	
	@RequestMapping(value="/incidence/add", method=RequestMethod.POST )
	public String setMark(@ModelAttribute Agent agente,Incidence incidence) {//@ModelAttribute Mark mark){
		
		incidenceService.isCorrectIncidence(agente.getName(), agente.getPassword(), incidence);
		return "redirect:/index";
	}
}
