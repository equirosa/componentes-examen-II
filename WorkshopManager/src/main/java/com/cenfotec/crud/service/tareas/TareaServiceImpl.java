package com.cenfotec.crud.service.tareas;

import com.cenfotec.crud.domain.Tarea;
import com.cenfotec.crud.repo.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {

	@Autowired
	TareaRepository repo;

	@Override
	public void save(Tarea workshop) {
		repo.save(workshop);
	}

	@Override
	public Optional<Tarea> get(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Tarea> find(String name) {
		return repo.findByNameContaining(name);
	}

	@Override
	public List<Tarea> getAll() {
		return repo.findAll();
	}

}
