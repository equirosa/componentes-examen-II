package com.cenfotec.crud.service.categorias;

import com.cenfotec.crud.domain.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoriaService {

	void save(Categoria workshop);

	Optional<Categoria> get(Long id);

	List<Categoria> getAll();

	List<Categoria> find(String name);

}
