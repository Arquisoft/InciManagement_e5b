package uo.asw.dbManagement.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Incidence {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String identifier;

	private String login;
	private String password;
	private String kind;

	private String operatorIdentifier;

	private String name;
	private String description;
	private String location;

	@ElementCollection
	@CollectionTable(name = "incidence_tags", joinColumns = @JoinColumn(name = "incidence_ID"))
	private Set<String> tags;

	@ElementCollection
	@CollectionTable(name = "incidence_properties", joinColumns = @JoinColumn(name = "incidence_ID"))
	private Set<Property> properties = new HashSet<>();

	private String status; // open, in process, closed, canceled
	private String operatorComments;
	private String expiration;

	public Incidence() {
	}

	public Incidence(String identifier) {
		this.identifier = identifier;
	}

	public long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public Incidence setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Incidence setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getKind() {
		return kind;
	}

	public Incidence setKind(String kind) {
		this.kind = kind;
		return this;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	public Incidence setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Incidence setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public Incidence setLocation(String location) {
		this.location = location;
		return this;
	}

	public Set<String> getTags() {
		return tags;
	}

	public Incidence setTags(Set<String> tags) {
		this.tags = tags;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Incidence setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getOperatorComments() {
		return operatorComments;
	}

	public Incidence setOperatorComments(String operatorComments) {
		this.operatorComments = operatorComments;
		return this;
	}

	public String getExpiration() {
		return expiration;
	}

	public Incidence setExpiration(String expiration) {
		this.expiration = expiration;
		return this;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public Incidence setProperties(Set<Property> properties) {
		this.properties = properties;
		return this;
	}

	public Incidence setIdentifier(String identifier) {
		this.identifier = identifier;
		return this;
	}

	public String getOperatorIdentifier() {
		return operatorIdentifier;
	}

	public Incidence setOperatorIdentifier(String operatorIdentifier) {
		this.operatorIdentifier = operatorIdentifier;
		return this;

	}

	@Override
	public String toString() {
		return "Incidence [id=" + id + ", identifier=" + identifier + ", login=" + login + ", password=" + password
				+ ", kind=" + kind + ", operatorIdentifier=" + operatorIdentifier + ", name=" + name + ", description="
				+ description + ", location=" + location + ", tags=" + tags + ", properties=" + properties + ", status="
				+ status + ", operatorComments=" + operatorComments + ", expiration=" + expiration + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incidence other = (Incidence) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

	/**
	 * Permite comparar si esta incidencia y la que se pasa como parametro
	 * tienen todos sus campos iguales. Es distinto del equals, ya que el equals
	 * solo compara si las dos incidencias tienen el mismo "identifier", pero
	 * este metodo permite realizar pruebas más exhaustivas
	 * 
	 * @param i
	 * @return
	 */
	public boolean equalFields(Incidence i) {
		if (this == i)
			return true;
		if (i == null)
			return false;
		if (getClass() != i.getClass())
			return false;

		if (login == null) {
			if (i.login != null)
				return false;
		} else if (!login.equals(i.login))
			return false;
		if (description == null) {
			if (i.description != null)
				return false;
		} else if (!description.equals(i.description))
			return false;
		if (expiration == null) {
			if (i.expiration != null)
				return false;
		} else if (!expiration.equals(i.expiration))
			return false;
		if (identifier == null) {
			if (i.identifier != null)
				return false;
		} else if (!identifier.equals(i.identifier))
			return false;
		if (location == null) {
			if (i.location != null)
				return false;
		} else if (!location.equals(i.location))
			return false;
		if (name == null) {
			if (i.name != null)
				return false;
		} else if (!name.equals(i.name))
			return false;
		if (operatorIdentifier == null) {
			if (i.operatorIdentifier != null)
				return false;
		} else if (!operatorIdentifier.equals(i.operatorIdentifier))
			return false;
		if (operatorComments == null) {
			if (i.operatorComments != null)
				return false;
		} else if (!operatorComments.equals(i.operatorComments))
			return false;
		if (properties == null) {
			if (i.properties != null)
				return false;
		} else if (!properties.equals(i.properties))
			return false;
		if (status == null) {
			if (i.status != null)
				return false;
		} else if (!status.equals(i.status))
			return false;
		if (tags == null) {
			if (i.tags != null)
				return false;
		} else if (!tags.equals(i.tags))
			return false;
		return true;
	}

}
