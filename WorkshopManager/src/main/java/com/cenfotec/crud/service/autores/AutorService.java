package com.cenfotec.crud.service.autores;

import com.cenfotec.crud.domain.Autor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AutorService {
	void save(Autor autor);

	Optional<Autor> get(Long id);

	List<Autor> getAll();

	List<Autor> find(String name);

}
