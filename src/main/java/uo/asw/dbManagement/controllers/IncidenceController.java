package uo.asw.dbManagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IncidenceController {
	@RequestMapping(value="/incidence/add")
	public String getMark(Model model){
		//model.addAttribute("usersList", usersService.getUsers());
		return "incidence/add";
	}
	
	@RequestMapping(value="/incidence/add", method=RequestMethod.POST )
	public String setMark() {//@ModelAttribute Mark mark){
		//marksService.addMark(mark);
		return "redirect:/index";
	}
}
