package uo.asw.dbManagement;

import org.springframework.beans.factory.annotation.Autowired;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.repositories.AgentsRepository;

public class GetAgentImpl implements GetAgent {

	@Autowired
	private AgentsRepository agentsRepository;
	
	@Override
	public Agent getAgent(String login) {
		return agentsRepository.getAgent(login);
	}

}
