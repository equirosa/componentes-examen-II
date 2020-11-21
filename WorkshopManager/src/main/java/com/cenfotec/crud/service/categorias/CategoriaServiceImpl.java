package com.cenfotec.crud.service.categorias;

import com.cenfotec.crud.domain.Categoria;
import com.cenfotec.crud.repo.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository repo;

	@Override
	public void save(Categoria workshop) {
		repo.save(workshop);
	}

	@Override
	public Optional<Categoria> get(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Categoria> find(String name) {
		return repo.findByNameContaining(name);
	}

	@Override
	public List<Categoria> getAll() {
		return repo.findAll();
	}

}
