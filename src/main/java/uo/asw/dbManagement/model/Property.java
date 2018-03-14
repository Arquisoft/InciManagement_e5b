package uo.asw.dbManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Property {

		@Id
		@GeneratedValue
		private long id; 
		private String property;
		private String value;
		
		@ManyToOne
		@JoinColumn(name="incidence_id")
		private Incidence incidence;
		
		public Property() {}
		
		public Property(String property, String value) {
			this.property=property;
			this.value=value;
		}

		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}

		public Incidence getIncidence() {
			return incidence;
		}

		public void setIncidence(Incidence incidence) {
			this.incidence = incidence;
		}

		@Override
		public String toString() {
			return "Property [id=" + id + ", property=" + property + ", value=" + value + ", incidence=" + incidence
					+ "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((property == null) ? 0 : property.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
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
			Property other = (Property) obj;
			if (property == null) {
				if (other.property != null)
					return false;
			} else if (!property.equals(other.property))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
}
