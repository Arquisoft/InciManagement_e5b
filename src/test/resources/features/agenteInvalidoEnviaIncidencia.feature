Feature: agente con datos invalidos envia una incidencia
 
	  Scenario: agente NO existente en la aplicacion añade una incidencia básica
			    Given el agente con login "AAAAAA" , el password "1234" y el kind "Person"
			    	And los siguientes datos de la incidencia: name "Nombre" y description "Descripcion"
			    		
			    When el agente envía la incidencia no valida
			    
			    Then la incidencia no se envia