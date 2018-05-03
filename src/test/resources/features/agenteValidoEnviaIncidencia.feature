Feature: agente con datos validos envia una incidencia
 
	  Scenario: agente existente en la aplicacion añade una incidencia básica
			    Given el agente con el login "31668313G" , el password "1234" y el kind "Person"
			    	And los siguientes datos de la incidencia: name "Nombre" , description "Descripcion"
			    		
			    When el agente envía la incidencia
			    
			    Then la incidencia es enviada correctamente