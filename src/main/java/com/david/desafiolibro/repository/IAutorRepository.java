package com.david.desafiolibro.repository;

import com.david.desafiolibro.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository <Autor, Long> {
    Optional<Autor>findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :año AND (a.fechaMuerte IS NULL OR a.fechaMuerte >= :año)")
    List<Autor> buscarAutoresVivosEnDeterminadoAño(int año);

}
