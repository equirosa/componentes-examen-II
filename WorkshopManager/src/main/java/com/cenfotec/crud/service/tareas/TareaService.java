package com.cenfotec.crud.service.tareas;

import com.cenfotec.crud.domain.Tarea;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TareaService {

	void save(Tarea workshop);

	Optional<Tarea> get(Long id);

	List<Tarea> getAll();

	List<Tarea> find(String name);

}
