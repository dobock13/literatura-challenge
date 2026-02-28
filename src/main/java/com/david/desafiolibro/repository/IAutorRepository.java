package com.david.desafiolibro.repository;

import com.david.desafiolibro.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAutorRepository extends JpaRepository <Autor, Long> {
    Optional<Autor>findByNombreIgnoreCase(String nombre);


}
