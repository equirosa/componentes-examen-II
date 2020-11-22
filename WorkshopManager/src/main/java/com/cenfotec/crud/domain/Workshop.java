package com.cenfotec.crud.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Workshop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name, autor, description, objective, keywords;
	private double duracionTotal = 0;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workshop")
	private Set<Tarea> tareas;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	public Workshop() {
	}

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

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public double getDuracionTotal() {
		return duracionTotal;
	}

	public void setDuracionTotal(double duracionTotal) {
		this.duracionTotal = duracionTotal;
	}

	public void setId(long id) {
		this.id = id;
	}
}

