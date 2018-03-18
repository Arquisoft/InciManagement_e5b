package uo.asw.reporter;

import uo.asw.dbManagement.model.Incidence;

public class InciReporter {
private static final String fileName = "reportLog.log";
	
	public static void reportInci(Incidence inci) {
		String message = "Prueba";
		Writer.write(fileName, message);
	}
}
