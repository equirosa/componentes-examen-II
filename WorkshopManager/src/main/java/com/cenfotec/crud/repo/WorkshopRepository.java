package com.cenfotec.crud.repo;

import com.cenfotec.crud.domain.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
	List<Workshop> findByNameContaining(String word);
}
