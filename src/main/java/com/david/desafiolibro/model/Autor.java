package com.david.desafiolibro.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor (){}


    public Autor(DatosAutor datosAutor){
        this.nombre=datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaMuerte = datosAutor.fechaMuerte();
    }

    @Override
    public String toString() {
        String librosNombres = libros.stream().map(Libro::getTitulo).collect(Collectors.joining(", "));
        return  String.format("""
                ------- AUTOR -------
                Nombre: %s
                Año de nacimiento: %d
                Año de fallecimiento: %d
                Libros: %s
                ---------------------
                """,nombre,fechaNacimiento, fechaMuerte,librosNombres)
                ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public void agregarLibro(Libro libro) {
        if (this.libros == null) {
            this.libros = new ArrayList<>();
        }
        this.libros.add(libro);
        libro.setAutor(this);}

}
