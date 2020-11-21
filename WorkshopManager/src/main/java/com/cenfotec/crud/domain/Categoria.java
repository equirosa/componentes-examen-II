package com.cenfotec.crud.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	private Set<Workshop> workshops;
}
