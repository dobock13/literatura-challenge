package com.david.desafiolibro.repository;


import com.david.desafiolibro.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILIbroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro>findByTituloIgnoreCase(String titulo);

}
