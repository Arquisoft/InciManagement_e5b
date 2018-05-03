package uo.asw.inciManager;

import java.util.UUID;

public class IdentifierGenerator {

	public static String getIdentifier() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
