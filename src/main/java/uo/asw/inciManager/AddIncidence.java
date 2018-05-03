package uo.asw.inciManager;

public interface AddIncidence {

	String addIncidence();

	String addIncidence(String login, String password, String kind, String name, String description, String location,
			String tags, String properties);

}
