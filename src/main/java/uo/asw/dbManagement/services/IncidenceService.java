package uo.asw.dbManagement.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import uo.asw.apacheKafka.producer.KafkaProducer;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.repositories.AgentsRepository;
import uo.asw.dbManagement.repositories.IncidencesRepository;
import uo.asw.dbManagement.repositories.OperatorsRepository;

@Service
public class IncidenceService {
	
	@Autowired
	private AgentsRepository agentsRepository;
	
	@Autowired
	private IncidencesRepository incidenceRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	
	public void sendCorrectIncidence(Incidence incidence) {
		incidenceRepository.save(incidence);
		kafkaProducer.send(incidence.getName(), incidence.getDescription());//javi comprueba esta linea tambien porfa thanks
		
	}
	
	public boolean isCorrectIncidence(String name,String password,Incidence incidence) {
		if(loginCorrecto(name,password)) {
			sendCorrectIncidence(incidence);
			return true;
		}
		else {
			//crear informe del error. Javi haz esto lo primero (importante)
			return false;
		}
		
	}
	
	public boolean loginCorrecto(String name,String password) {//Esto lo hago yo Mateo para hacerlo compatible con el login
		return false;
	}
	
	public boolean agenteExiste() {//hacer una busqueda en busca el agente por id en agentsrepository, ya inyectado arriba
		return false;
	}
	
	
	
	

}
