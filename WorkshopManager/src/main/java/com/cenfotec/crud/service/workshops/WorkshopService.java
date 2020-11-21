package com.cenfotec.crud.service.workshops;

import com.cenfotec.crud.domain.Workshop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WorkshopService {

	void save(Workshop workshop);

	Optional<Workshop> get(Long id);

	List<Workshop> getAll();

	List<Workshop> find(String name);

}
