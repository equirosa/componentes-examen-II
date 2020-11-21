package com.cenfotec.crud.repo;

import com.cenfotec.crud.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
	List<Autor> findAutorByNameContaining(String word);
}
