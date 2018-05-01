package uo.asw.inciReporter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import uo.asw.dbManagement.model.Incidence;

@Component
public class ReportIncidenceImpl implements ReportIncidence {
	
	private static final String fileName = "reportLog.log";
	
	@Override
	public void reportIncidence(Incidence incidence) {		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(new FileWriter(fileName, true));
		    writer.println(incidence);
		} catch (IOException e) {
		   System.err.println("Writter error");
		} finally {
			if(writer != null)
				writer.close();
		}
		
	}
}
