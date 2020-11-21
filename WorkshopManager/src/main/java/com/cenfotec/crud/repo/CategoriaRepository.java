package com.cenfotec.crud.repo;

import com.cenfotec.crud.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
	List<Categoria> findByNameContaining(String word);
}
