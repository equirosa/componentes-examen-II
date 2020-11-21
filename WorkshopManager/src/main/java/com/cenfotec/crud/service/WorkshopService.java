package com.cenfotec.crud.service;

import com.cenfotec.crud.domain.Workshop;

import java.util.List;
import java.util.Optional;

public interface WorkshopService {

	void save(Workshop workshop);

	Optional<Workshop> get(Long id);

	List<Workshop> getAll();

	List<Workshop> find(String name);

}
