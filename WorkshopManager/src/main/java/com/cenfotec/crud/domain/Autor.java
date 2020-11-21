package com.cenfotec.crud.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autor")
	private Set<Workshop> workshops;
}
