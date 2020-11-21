package com.cenfotec.crud.service.autores;

import com.cenfotec.crud.domain.Autor;
import com.cenfotec.crud.repo.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {
	@Autowired
	AutorRepository repo;

	@Override
	public void save(Autor autor) {
		repo.save(autor);
	}

	@Override
	public Optional<Autor> get(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Autor> find(String name) {
		return repo.findAutorByNameContaining(name);
	}

	@Override
	public List<Autor> getAll() {
		return repo.findAll();
	}

}
