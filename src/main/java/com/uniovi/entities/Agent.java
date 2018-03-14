package com.uniovi.entities;

import javax.persistence.*;

@Entity
@Table(name = "AGENT")
public class Agent {

	@Id
	@Column(name = "IDENTIFICADOR")
	private Long identificador; // para proximas entregas igual seria mejor
									// ponerlo a long

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "LOCALIZACION")
	private String localizacion;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "CONTRASENA")
	private String contrasena;

	// private String tipoNombre;//tipo mejor en enumerados o ints

	// Constructor vacio para JPA
	public Agent() {
	}

	public Agent(Long identificador, String nombre, String localizacion, String email, String tipo,
			String contrasena) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.email = email;
		this.tipo = tipo;
		this.contrasena = contrasena;
	}

	public Agent(Long identificador, String nombre, String localizacion, String email, String tipo) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.email = email;
		this.tipo = tipo;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return this.tipo;
	}

	// public void setTipoNombre(String tipoNombre){
	// this.tipoNombre = tipoNombre;
	// }
	//
	// public String getTipoNombre(String path){
	// Type tipo= new Type(path);
	// this.tipoNombre=tipo.getNombre(this.tipo);
	// return this.tipoNombre;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
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
		Agent other = (Agent) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agent [identificador=" + identificador + ", nombre=" + nombre + ", localizacion=" + localizacion
				+ ", email=" + email + ", tipo=" + tipo + "]";
	}

}