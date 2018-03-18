package uo.asw.reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
	public static void write(String fileName, String message) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(fileName, true));
		    writer.println(message);
		} catch (IOException e) {
		   System.err.println("Writter error");
		} finally {
			writer.close();
		}
	}
}
