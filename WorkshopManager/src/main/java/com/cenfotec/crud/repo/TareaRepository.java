package com.cenfotec.crud.repo;

import com.cenfotec.crud.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
	List<Tarea> findByNameContaining(String word);
}
