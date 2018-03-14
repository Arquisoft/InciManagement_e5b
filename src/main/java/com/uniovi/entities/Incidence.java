package com.uniovi.entities;
import java.util.Date;

import javax.persistence.*;


@Entity
public class Incidence {
	public Incidence() {
		
	}

	public Incidence(String name, String descripcion, String localizacion, Long id_agente, Date fecha_incidencia) {
		super();
		this.name = name;
		this.descripcion = descripcion;
		this.localizacion = localizacion;
		this.id_agente = id_agente;
		this.fecha_incidencia = fecha_incidencia;
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String descripcion;
	private String localizacion;
	
	@ManyToOne
	private Long id_agente;
	
	@Temporal(TemporalType.DATE)
	private Date fecha_incidencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Long getId_agente() {
		return id_agente;
	}

	public void setId_agente(Long id_agente) {
		this.id_agente = id_agente;
	}

	public Date getFecha_incidencia() {
		return fecha_incidencia;
	}

	public void setFecha_incidencia(Date fecha_incidencia) {
		this.fecha_incidencia = fecha_incidencia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
