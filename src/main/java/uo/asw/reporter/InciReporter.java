package uo.asw.reporter;

import uo.asw.dbManagement.model.Incidence;

public class InciReporter {
private static final String fileName = "reportLog.log";
	
	public static void reportInci(Incidence inci) {
		String message = "La incidencia " + inci.getDescription() + " no ha podido ser almacenada";
		Writer.write(fileName, message);
	}
}
