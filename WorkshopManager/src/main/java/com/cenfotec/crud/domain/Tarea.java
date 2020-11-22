package com.cenfotec.crud.domain;

import javax.persistence.*;

@Entity
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name, description, texto;
	private double duration;

	@ManyToOne
	@JoinColumn(name = "workshop_id", nullable = false)
	private Workshop workshop;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
